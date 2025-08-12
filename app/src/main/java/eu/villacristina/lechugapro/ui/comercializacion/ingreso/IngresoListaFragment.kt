package eu.villacristina.lechugapro.ui.comercializacion.ingreso

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import eu.villacristina.lechugapro.data.AppDatabase
import eu.villacristina.lechugapro.data.IngresoRepository
import eu.villacristina.lechugapro.databinding.FragmentIngresoListaBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class IngresoListaFragment : Fragment() {

    private var _binding: FragmentIngresoListaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: IngresoListaViewModel by viewModels {
        val database = AppDatabase.getDatabase(requireContext())
        IngresoListaViewModelFactory(IngresoRepository(database.ingresoDao()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIngresoListaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar el RecyclerView y su Adapter
        val adapter = IngresoListaAdapter { ingreso ->
            // TODO: Navegar a la pantalla de edición de un ingreso
            Toast.makeText(requireContext(), "Editar: ${ingreso.concepto}", Toast.LENGTH_SHORT).show()
        }
        binding.recyclerviewIngresos.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerviewIngresos.adapter = adapter

        // Observar la lista de ingresos del ViewModel y actualizar la UI
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.todosLosIngresos.collectLatest { ingresos ->
                binding.emptyView.isVisible = ingresos.isEmpty()
                binding.recyclerviewIngresos.isVisible = ingresos.isNotEmpty()
                adapter.submitList(ingresos)
            }
        }

        // Configurar el botón flotante (FAB) para añadir nuevos ingresos
        binding.fabAnadirIngreso.setOnClickListener {
            // TODO: Navegar a la pantalla de creación de un nuevo ingreso
            Toast.makeText(requireContext(), "Añadir nuevo ingreso", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}