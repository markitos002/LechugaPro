package eu.villacristina.lechugapro.ui.comercializacion.balance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import eu.villacristina.lechugapro.R
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import eu.villacristina.lechugapro.data.AppDatabase
import eu.villacristina.lechugapro.data.Gasto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GastosTabFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_gastos_tab, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerview_gastos)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val db = AppDatabase.getDatabase(requireContext())
        val adapter = GastoEditableAdapter { gastoActualizado ->
            // Guardado automático al modificar importe o fecha
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                db.gastoDao().updateGasto(gastoActualizado)
            }
        }
        recycler.adapter = adapter

        // Cargar gastos globales (tabla editable global)
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val gastos = db.gastoDao().getTodosGastos()
            withContext(Dispatchers.Main) { adapter.submitList(gastos) }
        }
    }
}
