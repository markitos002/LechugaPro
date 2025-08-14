package eu.villacristina.lechugapro.ui.comercializacion.cliente

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import eu.villacristina.lechugapro.data.AppDatabase
import eu.villacristina.lechugapro.data.ClienteRepository
import eu.villacristina.lechugapro.data.IngresoRepository
import eu.villacristina.lechugapro.databinding.FragmentClienteListaBinding
import eu.villacristina.lechugapro.util.CurrencyFormatter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ClienteListaFragment : Fragment() {

    private var _binding: FragmentClienteListaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ClienteListaViewModel by viewModels {
        val db = AppDatabase.getDatabase(requireContext())
        ClienteListaViewModelFactory(
            ClienteRepository(db.clienteDao()),
            IngresoRepository(db.ingresoDao())
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClienteListaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ClienteListaAdapter { cliente ->
            // **CAMBIO AQUÍ: NAVEGACIÓN A DETALLE ACTIVADA**
            val action = ClienteListaFragmentDirections.actionClienteListaFragmentToClienteDetalleFragment(cliente.id)
            findNavController().navigate(action)
        }

        binding.recyclerviewClientes.adapter = adapter
        binding.recyclerviewClientes.layoutManager = LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.todosLosClientes.collectLatest { clientes ->
                adapter.submitList(clientes)
            }
        }

        // Bind total ingresos
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.totalIngresos.collectLatest { total ->
                binding.textTotalIngresos.text = "Ingreso Total: ${CurrencyFormatter.formatPeso(total)}"
            }
        }

        binding.fabAddCliente.setOnClickListener {
            val action = ClienteListaFragmentDirections.actionClienteListaFragmentToClienteEditFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}