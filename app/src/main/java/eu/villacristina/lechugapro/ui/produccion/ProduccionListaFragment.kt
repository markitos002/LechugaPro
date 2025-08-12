package eu.villacristina.lechugapro.ui.produccion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import eu.villacristina.lechugapro.data.CicloProduccionRepository
import eu.villacristina.lechugapro.databinding.FragmentProduccionListaBinding
import eu.villacristina.lechugapro.LechugaProApplication // Importamos nuestra clase Application

class ProduccionListaFragment : Fragment() {

    private var _binding: FragmentProduccionListaBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProduccionListaViewModel
    private lateinit var produccionListaAdapter: ProduccionListaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProduccionListaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtenemos la instancia de la base de datos desde nuestra clase Application
        val application = requireActivity().application as LechugaProApplication
        val cicloProduccionDao = application.database.cicloProduccionDao()
        val repository = CicloProduccionRepository(cicloProduccionDao)
        val viewModelFactory = ProduccionListaViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory)[ProduccionListaViewModel::class.java]

        setupRecyclerView()

        viewModel.todosLosCiclos.observe(viewLifecycleOwner) { ciclos ->
            ciclos?.let {
                produccionListaAdapter.submitList(it)
            }
        }

        binding.fabAgregarCiclo.setOnClickListener {
            // Navega a la pantalla de edición/creación de ciclo
            val action = ProduccionListaFragmentDirections.actionProduccionListaFragmentToProduccionEditFragment()
            findNavController().navigate(action)
        }
    }

    private fun setupRecyclerView() {
        produccionListaAdapter = ProduccionListaAdapter { cicloSeleccionado ->
            val action = ProduccionListaFragmentDirections.actionProduccionListaFragmentToProduccionDetalleFragment(cicloSeleccionado.id)
            findNavController().navigate(action)
        }

        binding.recyclerViewCiclos.apply {
            adapter = produccionListaAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}