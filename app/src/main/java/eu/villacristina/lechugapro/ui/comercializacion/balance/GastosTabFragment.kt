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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import android.content.DialogInterface
import android.widget.Toast
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
        val cicloProduccionDao = db.cicloProduccionDao()
        val adapter = GastoEditableAdapter { gastoActualizado ->
            // Guardado automático al modificar importe o fecha
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                db.gastoDao().updateGasto(gastoActualizado)
            }
        }
        recycler.adapter = adapter

        // Cargar gastos filtrando por ciclo activo (si existe) o globales si no
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val cicloActivo = cicloProduccionDao.getUltimoActivo()
            val gastos = if (cicloActivo != null) db.gastoDao().getGastosPorCiclo(cicloActivo.id) else db.gastoDao().getTodosGastos()
            val ordenados = gastos.sortedWith(compareBy<Gasto> { it.tipo != "Plantulas" }.thenBy { it.tipo })
            withContext(Dispatchers.Main) {
                adapter.submitList(ordenados)
                if (cicloActivo == null) {
                    Toast.makeText(requireContext(), "No hay ciclo activo; mostrando gastos globales", Toast.LENGTH_SHORT).show()
                }
                if (ordenados.isEmpty()) {
                    Toast.makeText(requireContext(), "No hay gastos para mostrar", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // FAB para añadir nuevo tipo de gasto (global si no hay ciclo activo)
    view.findViewById<View>(R.id.fab_add_tipo).setOnClickListener {
            val input = android.widget.EditText(requireContext())
            input.hint = "Nombre del tipo (p. ej. Diesel)"
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Nuevo tipo de gasto")
                .setView(input)
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Añadir") { _: DialogInterface, _: Int ->
                    val nombre = input.text?.toString()?.trim().orEmpty()
                    if (nombre.isNotEmpty()) {
                        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                            val cicloActivo = cicloProduccionDao.getUltimoActivo()
                            val duplicate = when {
                                cicloActivo != null -> db.gastoDao().countByTipoEnCiclo(cicloActivo.id, nombre) > 0
                                else -> db.gastoDao().countByTipoGlobal(nombre) > 0
                            }
                            if (duplicate) {
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(requireContext(), "Ya existe el tipo '$nombre' en este ámbito", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                val nuevo = Gasto(idCiclo = cicloActivo?.id ?: 0, tipo = nombre, importe = 0.0, fecha = System.currentTimeMillis())
                                db.gastoDao().insertGasto(nuevo)
                            }
                            val actualizados = if (cicloActivo != null) db.gastoDao().getGastosPorCiclo(cicloActivo.id) else db.gastoDao().getTodosGastos()
                            val orden = actualizados.sortedWith(compareBy<Gasto> { it.tipo != "Plantulas" }.thenBy { it.tipo })
                            withContext(Dispatchers.Main) { adapter.submitList(orden) }
                        }
                    }
                }
                .show()
        }
    }
}
