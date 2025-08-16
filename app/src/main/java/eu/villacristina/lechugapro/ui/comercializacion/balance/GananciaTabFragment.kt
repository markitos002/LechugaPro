package eu.villacristina.lechugapro.ui.comercializacion.balance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import eu.villacristina.lechugapro.R
import eu.villacristina.lechugapro.data.AppDatabase
import eu.villacristina.lechugapro.util.CurrencyFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GananciaTabFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ganancia_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textIngresos = view.findViewById<TextView>(R.id.text_total_ingresos)
        val textGastos = view.findViewById<TextView>(R.id.text_total_gastos)
        val textGanancia = view.findViewById<TextView>(R.id.text_ganancia)
        val btnRefrescar = view.findViewById<View>(R.id.btn_refrescar_ganancia)

        fun cargarDatos() {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                val db = AppDatabase.getDatabase(requireContext())
                val ingresosTotal = db.ingresoDao().sumImporteTotal()
                // Gastos: si hay ciclo activo, usar esos; si no, globales (0)
                val cicloActivo = db.cicloProduccionDao().getUltimoActivo()
                val gastosTotal = if (cicloActivo != null) db.gastoDao().sumImportePorCiclo(cicloActivo.id) else db.gastoDao().sumImporteGlobal()
                val ganancia = ingresosTotal - gastosTotal
                withContext(Dispatchers.Main) {
                    textIngresos.text = "Total ingresos: ${CurrencyFormatter.formatPeso(ingresosTotal)}"
                    textGastos.text = "Total gastos: ${CurrencyFormatter.formatPeso(gastosTotal)}"
                    textGanancia.text = "Ganancia: ${CurrencyFormatter.formatPeso(ganancia)}"
                }
            }
        }

        btnRefrescar.setOnClickListener { cargarDatos() }
        cargarDatos()
    }
}
