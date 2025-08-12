package eu.villacristina.lechugapro.ui.produccion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import eu.villacristina.lechugapro.data.CicloProduccion
import eu.villacristina.lechugapro.data.CicloProduccionRepository
import eu.villacristina.lechugapro.databinding.FragmentProduccionDetalleBinding
import eu.villacristina.lechugapro.LechugaProApplication // Importamos nuestra clase Application
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProduccionDetalleFragment : Fragment() {

    private var _binding: FragmentProduccionDetalleBinding? = null
    private val binding get() = _binding!!

    private val args: ProduccionDetalleFragmentArgs by navArgs()
    private val viewModel: ProduccionDetalleViewModel by viewModels {
        val application = requireActivity().application as LechugaProApplication
        val cicloProduccionDao = application.database.cicloProduccionDao()
        ProduccionDetalleViewModelFactory(
            CicloProduccionRepository(cicloProduccionDao),
            args.cicloId
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProduccionDetalleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observar el LiveData del ViewModel
        viewModel.ciclo.observe(viewLifecycleOwner, Observer { ciclo ->
            ciclo?.let {
                bindCiclo(it)
                // **NUEVO**: Actualizamos la visibilidad de los botones cada vez que cambia el ciclo
                actualizarVisibilidadBotones(it)
            }
        })

        // Listener para el botón de editar
        binding.fabEditarCiclo.setOnClickListener {
            val action = ProduccionDetalleFragmentDirections.actionProduccionDetalleFragmentToProduccionEditFragment(args.cicloId)
            findNavController().navigate(action)
        }

        // **NUEVO**: Listeners para los botones de cambio de estado
        setupActionListeners()
    }

    private fun bindCiclo(ciclo: CicloProduccion) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        binding.detalleNombreCiclo.text = ciclo.nombreCiclo ?: "Sin nombre"
        binding.detalleEstado.text = ciclo.estado
        binding.detalleNumeroPlantas.text = ciclo.numeroPlantas.toString()
        binding.detalleFechaSiembra.text = ciclo.fechaSiembra?.let { dateFormat.format(Date(it)) } ?: "N/A"
        binding.detalleNotas.text = ciclo.notas ?: "Sin notas"
    }

    /**
     * NUEVA FUNCIÓN
     * Configura los listeners para los botones de acción.
     */
    private fun setupActionListeners() {
        binding.buttonIniciarCiclo.setOnClickListener {
            viewModel.cambiarEstadoCiclo("Activo")
        }
        binding.buttonTerminarCiclo.setOnClickListener {
            viewModel.cambiarEstadoCiclo("Terminado")
        }
    }

    /**
     * NUEVA FUNCIÓN
     * Muestra y oculta los botones de acción según el estado actual del ciclo.
     */
    private fun actualizarVisibilidadBotones(ciclo: CicloProduccion) {
        binding.buttonIniciarCiclo.isVisible = ciclo.estado == "Planificado"
        binding.buttonTerminarCiclo.isVisible = ciclo.estado == "Activo"
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}