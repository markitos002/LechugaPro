package eu.villacristina.lechugapro.ui.comercializacion.cliente

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import eu.villacristina.lechugapro.R
import eu.villacristina.lechugapro.data.AppDatabase
import eu.villacristina.lechugapro.data.Cliente
import eu.villacristina.lechugapro.data.ClienteRepository
import eu.villacristina.lechugapro.data.IngresoRepository
import eu.villacristina.lechugapro.databinding.FragmentClienteDetalleBinding
// --- IMPORT CORREGIDO ---
import eu.villacristina.lechugapro.ui.comercializacion.ingreso.IngresoListaAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ClienteDetalleFragment : Fragment() {

    private var _binding: FragmentClienteDetalleBinding? = null
    private val binding get() = _binding!!

    private val args: ClienteDetalleFragmentArgs by navArgs()

    private val viewModel: ClienteDetalleViewModel by viewModels {
        val database = AppDatabase.getDatabase(requireContext())
        ClienteDetalleViewModelFactory(
            ClienteRepository(database.clienteDao()),
            IngresoRepository(database.ingresoDao()),
            args.clienteId
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true) // Habilitar el menú en la AppBar
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClienteDetalleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // --- Configuración del Adapter y RecyclerView de Ingresos ---
        val ingresoAdapter = IngresoListaAdapter { ingreso ->
            // TODO: Navegar a la pantalla de edición de un ingreso existente
            Toast.makeText(requireContext(), "Editar ingreso: ${ingreso.concepto}", Toast.LENGTH_SHORT).show()
        }
        binding.recyclerviewIngresos.adapter = ingresoAdapter
        binding.recyclerviewIngresos.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerviewIngresos.isNestedScrollingEnabled = false // Importante para el scroll suave

        // --- Observadores de datos del ViewModel ---
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.cliente.collectLatest { cliente ->
                cliente?.let { bindCliente(it) }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.ingresos.collectLatest { ingresos ->
                ingresoAdapter.submitList(ingresos)
            }
        }

        // --- Lógica del FAB para añadir nuevo ingreso ---
        binding.fabAnadirIngreso.setOnClickListener {
            // TODO: Navegar a la pantalla de creación de un nuevo ingreso. Necesita nav_graph.
            Toast.makeText(requireContext(), "Añadir nuevo ingreso", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detalle, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                val action = ClienteDetalleFragmentDirections.actionClienteDetalleFragmentToClienteEditFragment(args.clienteId)
                findNavController().navigate(action)
                true
            }
            R.id.action_delete -> {
                // TODO: Implementar lógica de borrado con diálogo de confirmación
                Toast.makeText(requireContext(), "Borrar cliente", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun bindCliente(cliente: Cliente) {
        requireActivity().title = cliente.nombreCompleto // Cambiar título de la AppBar
        binding.detalleNombreCliente.text = cliente.nombreCompleto
        binding.detalleTelefonoCliente.text = cliente.telefono.takeIf { !it.isNullOrBlank() } ?: "No especificado"
        binding.detalleEmailCliente.text = cliente.email.takeIf { !it.isNullOrBlank() } ?: "No especificado"
        binding.detalleDireccionCliente.text = cliente.direccion.takeIf { !it.isNullOrBlank() } ?: "No especificada"
        binding.detalleNotasCliente.text = cliente.notasCliente.takeIf { !it.isNullOrBlank() } ?: "Sin notas"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}