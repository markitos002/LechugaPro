package eu.villacristina.lechugapro.ui.produccion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.villacristina.lechugapro.data.CicloProduccion
import eu.villacristina.lechugapro.databinding.ItemCicloProduccionBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProduccionListaAdapter(
    private val onItemClicked: (CicloProduccion) -> Unit
) : ListAdapter<CicloProduccion, ProduccionListaAdapter.CicloProduccionViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CicloProduccionViewHolder {
        val binding = ItemCicloProduccionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CicloProduccionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CicloProduccionViewHolder, position: Int) {
        val cicloActual = getItem(position)
        holder.bind(cicloActual)
        holder.itemView.setOnClickListener {
            onItemClicked(cicloActual)
        }
    }

    inner class CicloProduccionViewHolder(private val binding: ItemCicloProduccionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        fun bind(ciclo: CicloProduccion) {
            // Usar las propiedades correctas generadas por ViewBinding
            binding.cicloItemNombre.text = ciclo.nombreCiclo ?: "Ciclo sin nombre"
            binding.cicloItemEstado.text = ciclo.estado

            // Asignar la fecha de siembra al campo de fecha
            binding.cicloItemFecha.text = ciclo.fechaSiembra?.let {
                "Siembra: " + dateFormat.format(Date(it))
            } ?: "Fecha N/A"
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CicloProduccion>() {
        override fun areItemsTheSame(oldItem: CicloProduccion, newItem: CicloProduccion): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CicloProduccion, newItem: CicloProduccion): Boolean {
            return oldItem == newItem
        }
    }
}
