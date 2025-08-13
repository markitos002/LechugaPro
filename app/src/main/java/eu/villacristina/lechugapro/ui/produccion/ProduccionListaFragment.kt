package eu.villacristina.lechugapro.ui.produccion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import eu.villacristina.lechugapro.LechugaProApplication
import eu.villacristina.lechugapro.data.CicloProduccion
import eu.villacristina.lechugapro.data.CicloProduccionRepository
import eu.villacristina.lechugapro.databinding.FragmentProduccionListaBinding
import androidx.navigation.fragment.findNavController
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.coroutines.launch

class ProduccionListaFragment : Fragment() {
    private var _binding: FragmentProduccionListaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProduccionListaViewModel by viewModels {
        val app = requireActivity().application as LechugaProApplication
        val repo = CicloProduccionRepository(app.database.cicloProduccionDao())
        ProduccionListaViewModel.Factory(repo)
    }

    private lateinit var adapter: CicloAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProduccionListaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        observeData()
        binding.fabAdd.setOnClickListener {
            val action = ProduccionListaFragmentDirections.actionProduccionListaFragmentToProduccionEditFragment()
            findNavController().navigate(action)
        }
    }

    private fun setupRecycler() {
        adapter = CicloAdapter { ciclo ->
            // Navegar a edición de ciclo existente
                val action = ProduccionListaFragmentDirections.actionProduccionListaFragmentToProduccionDetalleFragment(ciclo.id)
            findNavController().navigate(action)
        }
        binding.recyclerCiclos.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerCiclos.adapter = adapter
    }

    private fun observeData() {
        viewModel.ciclos.observe(viewLifecycleOwner) { lista ->
            adapter.submit(lista)
            binding.textEmpty.visibility = if (lista.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class ProduccionListaViewModel(private val repository: CicloProduccionRepository) : ViewModel() {
    val ciclos = repository.todosLosCiclos

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: CicloProduccionRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProduccionListaViewModel::class.java)) {
                return ProduccionListaViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

private class CicloAdapter(private val onClick: (CicloProduccion) -> Unit) : androidx.recyclerview.widget.RecyclerView.Adapter<CicloVH>() {
    private val items = mutableListOf<CicloProduccion>()
    fun submit(nuevos: List<CicloProduccion>) {
        items.clear(); items.addAll(nuevos); notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CicloVH {
        val v = LayoutInflater.from(parent.context).inflate(eu.villacristina.lechugapro.R.layout.item_ciclo_produccion, parent, false)
        return CicloVH(v)
    }
    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: CicloVH, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onClick(item) }
    }
}

private class CicloVH(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    private val nombre = view.findViewById<android.widget.TextView>(eu.villacristina.lechugapro.R.id.text_nombre)
    private val variedad = view.findViewById<android.widget.TextView>(eu.villacristina.lechugapro.R.id.text_variedad)
    private val estado = view.findViewById<android.widget.TextView>(eu.villacristina.lechugapro.R.id.text_estado)
    private val fechas = view.findViewById<android.widget.TextView>(eu.villacristina.lechugapro.R.id.text_fechas)
    private val df = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    fun bind(c: CicloProduccion) {
        nombre.text = c.nombreCiclo ?: "(Sin nombre)"
        variedad.text = "Variedad: ${c.variedad ?: "-"}"
        estado.text = "Estado: ${c.estado}"
        val siembra = c.fechaSiembra?.let { df.format(Date(it)) } ?: "?"
        val estimada = c.fechaEstimadaCosecha?.let { df.format(Date(it)) } ?: "?"
        fechas.text = "Siembra: $siembra • Estimada cosecha: $estimada"
    }
}
