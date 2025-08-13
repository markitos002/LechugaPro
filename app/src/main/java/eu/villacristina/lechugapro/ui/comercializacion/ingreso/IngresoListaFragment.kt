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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentIngresoListaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = IngresoListaAdapter { ingreso ->
            val action = IngresoListaFragmentDirections.actionIngresoListaFragmentToIngresoEditFragment(
                ingresoId = ingreso.id,
                clienteId = ingreso.idCliente
            )
            findNavController().navigate(action)
        }
        binding.recyclerviewIngresos.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerviewIngresos.adapter = adapter

        val swipeCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) = false
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return
                val ingreso = adapter.currentList.getOrNull(position)
                if (ingreso != null) {
                    viewModel.deleteIngreso(ingreso)
                    Snackbar.make(binding.root, "Ingreso borrado", Snackbar.LENGTH_LONG)
                        .setAction("DESHACER") { viewModel.reInsertIngreso(ingreso) }
                        .show()
                }
            }
        }
        ItemTouchHelper(swipeCallback).attachToRecyclerView(binding.recyclerviewIngresos)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.todosLosIngresos.collectLatest { ingresos ->
                binding.emptyView.isVisible = ingresos.isEmpty()
                binding.recyclerviewIngresos.isVisible = ingresos.isNotEmpty()
                adapter.submitList(ingresos)
            }
        }

        binding.fabAnadirIngreso.setOnClickListener {
            // No tenemos contexto de cliente; instruir al usuario
            Toast.makeText(requireContext(), "Añade ingresos desde el detalle de un cliente", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}