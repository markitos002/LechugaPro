package eu.villacristina.lechugapro.ui.comercializacion.balance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import eu.villacristina.lechugapro.R
import eu.villacristina.lechugapro.data.AppDatabase
import eu.villacristina.lechugapro.data.TotalPorCliente
import eu.villacristina.lechugapro.util.CurrencyFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IngresosTabFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ingresos_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = view.findViewById<RecyclerView>(R.id.recycler_totales_clientes)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        val adapter = TotalesClienteAdapter()
        recycler.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val db = AppDatabase.getDatabase(requireContext())
            val totales = db.ingresoDao().getTotalesPorCliente()
            withContext(Dispatchers.Main) { adapter.submit(totales) }
        }
    }
}

private class TotalesClienteAdapter : RecyclerView.Adapter<TotalVH>() {
    private val items = mutableListOf<TotalPorCliente>()
    fun submit(nuevos: List<TotalPorCliente>) { items.clear(); items.addAll(nuevos); notifyDataSetChanged() }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TotalVH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_total_cliente, parent, false)
        return TotalVH(v)
    }
    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: TotalVH, position: Int) = holder.bind(items[position])
}

private class TotalVH(view: View) : RecyclerView.ViewHolder(view) {
    private val nombre: TextView = view.findViewById(R.id.text_nombre_cliente)
    private val total: TextView = view.findViewById(R.id.text_total_cliente)
    fun bind(t: TotalPorCliente) {
        nombre.text = t.nombre
        total.text = CurrencyFormatter.formatPeso(t.total)
    }
}
