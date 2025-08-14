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
import eu.villacristina.lechugapro.R
import kotlinx.coroutines.launch

class ProduccionHistorialFragment : Fragment() {
    private val viewModel: ProduccionHistorialViewModel by viewModels {
        val app = requireActivity().application as LechugaProApplication
        val repo = CicloProduccionRepository(app.database.cicloProduccionDao())
        ProduccionHistorialViewModel.Factory(repo)
    }

    private lateinit var recycler: androidx.recyclerview.widget.RecyclerView
    private lateinit var empty: android.widget.TextView
    private lateinit var adapter: HistAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.fragment_produccion_historial, container, false)
        recycler = v.findViewById(R.id.recycler_historial)
        empty = v.findViewById(R.id.text_empty_hist)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = HistAdapter(
            onRestore = { c -> viewModel.restaurar(c.id) },
            onDelete = { c -> viewModel.eliminar(c.id) }
        )
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter
        viewModel.ciclos.observe(viewLifecycleOwner) { lista ->
            adapter.submit(lista)
            empty.visibility = if (lista.isEmpty()) View.VISIBLE else View.GONE
        }
    }
}

class ProduccionHistorialViewModel(private val repository: CicloProduccionRepository) : ViewModel() {
    val ciclos = repository.ciclosArchivados

    fun restaurar(id: Long) {
        viewModelScope.launch {
            val actual = repository.obtenerCicloPorId(id).value ?: return@launch
            repository.update(actual.copy(estado = "Planificado"))
        }
    }

    fun eliminar(id: Long) {
        viewModelScope.launch { repository.eliminar(id) }
    }

    class Factory(private val repository: CicloProduccionRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProduccionHistorialViewModel::class.java)) {
                return ProduccionHistorialViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

private class HistAdapter(
    private val onRestore: (CicloProduccion) -> Unit,
    private val onDelete: (CicloProduccion) -> Unit
) : androidx.recyclerview.widget.RecyclerView.Adapter<HistVH>() {
    private val items = mutableListOf<CicloProduccion>()
    fun submit(nuevos: List<CicloProduccion>) { items.clear(); items.addAll(nuevos); notifyDataSetChanged() }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistVH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_ciclo_produccion_hist, parent, false)
        return HistVH(v, onRestore, onDelete)
    }
    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: HistVH, position: Int) = holder.bind(items[position])
}

private class HistVH(view: View, val onRestore: (CicloProduccion) -> Unit, val onDelete: (CicloProduccion) -> Unit) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    private val nombre = view.findViewById<android.widget.TextView>(R.id.text_nombre)
    private val subtitulo = view.findViewById<android.widget.TextView>(R.id.text_sub)
    private val btnRestore = view.findViewById<com.google.android.material.button.MaterialButton>(R.id.btn_restore)
    private val btnDelete = view.findViewById<com.google.android.material.button.MaterialButton>(R.id.btn_delete)
    fun bind(c: CicloProduccion) {
        nombre.text = c.numeroCiclo?.toString() ?: "(Sin número)"
        val varTxt = c.variedad ?: "-"
        val plantas = c.numeroPlantas
        subtitulo.text = "Variedad: $varTxt • Plantas: $plantas"
        btnRestore.setOnClickListener { onRestore(c) }
        btnDelete.setOnClickListener { onDelete(c) }
    }
}
