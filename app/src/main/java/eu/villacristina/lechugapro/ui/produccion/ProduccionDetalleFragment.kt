package eu.villacristina.lechugapro.ui.produccion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import eu.villacristina.lechugapro.LechugaProApplication
import eu.villacristina.lechugapro.R
import eu.villacristina.lechugapro.data.CicloProduccion
import eu.villacristina.lechugapro.data.CicloProduccionRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope

class ProduccionDetalleFragment : Fragment() {
    private val args: ProduccionDetalleFragmentArgs by navArgs()

    private val viewModel: ProduccionDetalleViewModel by viewModels {
        val app = requireActivity().application as LechugaProApplication
        ProduccionDetalleViewModel.Factory(CicloProduccionRepository(app.database.cicloProduccionDao()), args.cicloId)
    }

    private lateinit var nombre: TextView
    private lateinit var estado: TextView
    private lateinit var variedad: TextView
    private lateinit var numero: TextView
    private lateinit var fechas: TextView
    private lateinit var notas: TextView
    private lateinit var btnIniciar: Button
    private lateinit var btnTerminar: Button
    private lateinit var btnEditar: Button
    private lateinit var btnArchivar: Button

    private val df = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_produccion_detalle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nombre = view.findViewById(R.id.detalle_nombre)
        estado = view.findViewById(R.id.detalle_estado)
        variedad = view.findViewById(R.id.detalle_variedad)
        numero = view.findViewById(R.id.detalle_numero)
        fechas = view.findViewById(R.id.detalle_fechas)
        notas = view.findViewById(R.id.detalle_notas)
        btnIniciar = view.findViewById(R.id.button_iniciar)
        btnTerminar = view.findViewById(R.id.button_terminar)
    btnEditar = view.findViewById(R.id.button_editar)
    btnArchivar = view.findViewById(R.id.button_archivar)

        viewModel.ciclo.observe(viewLifecycleOwner) { c ->
            c?.let { bind(it) }
        }

        btnIniciar.setOnClickListener { viewModel.cambiarEstado("Activo") }
        btnTerminar.setOnClickListener { viewModel.cambiarEstado("Terminado") }
        btnEditar.setOnClickListener {
            val action = ProduccionDetalleFragmentDirections.actionProduccionDetalleFragmentToProduccionEditFragment(args.cicloId)
            findNavController().navigate(action)
        }
        btnArchivar.setOnClickListener {
            val id = args.cicloId
            eu.villacristina.lechugapro.notifications.ReminderScheduler.cancelCycle(requireContext(), id)
            viewModel.archivar()
            android.widget.Toast.makeText(requireContext(), "Ciclo archivado", android.widget.Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    private fun bind(c: CicloProduccion) {
    nombre.text = c.numeroCiclo?.toString() ?: "(Sin número)"
        estado.text = "Estado: ${c.estado}"
        variedad.text = "Variedad: ${c.variedad ?: "-"}"
        numero.text = "Plantas: ${c.numeroPlantas}"
        val siembra = c.fechaSiembra?.let { df.format(Date(it)) } ?: "?"
        val estimada = c.fechaEstimadaCosecha?.let { df.format(Date(it)) } ?: "?"
        fechas.text = "Siembra: $siembra • Estimada: $estimada"
        notas.text = c.notas?.takeIf { it.isNotBlank() } ?: "Sin notas"
    btnIniciar.isVisible = c.estado == "Planificado"
    btnTerminar.isVisible = c.estado == "Activo"
    btnArchivar.isVisible = c.estado == "Terminado"
    }
}

class ProduccionDetalleViewModel(private val repository: CicloProduccionRepository, cicloId: Long) : ViewModel() {
    val ciclo = repository.obtenerCicloPorId(cicloId)

    fun cambiarEstado(nuevo: String) {
        val actual = ciclo.value ?: return
        if (actual.estado == nuevo) return
        val actualizado = actual.copy(estado = nuevo)
        viewModelScope.launch { repository.update(actualizado) }
    }

    fun archivar() {
        val actual = ciclo.value ?: return
        viewModelScope.launch { repository.archivar(actual.id) }
    }

    class Factory(private val repo: CicloProduccionRepository, private val id: Long) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProduccionDetalleViewModel::class.java)) {
                return ProduccionDetalleViewModel(repo, id) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
