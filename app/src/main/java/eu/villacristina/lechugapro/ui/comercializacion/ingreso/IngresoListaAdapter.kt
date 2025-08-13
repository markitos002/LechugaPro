package eu.villacristina.lechugapro.ui.comercializacion.ingreso

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.villacristina.lechugapro.data.Ingreso
import eu.villacristina.lechugapro.databinding.ItemIngresoBinding
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class IngresoListaAdapter(
    private val onItemClicked: (Ingreso) -> Unit
) : ListAdapter<Ingreso, IngresoListaAdapter.IngresoViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngresoViewHolder {
        val binding = ItemIngresoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngresoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngresoViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

    class IngresoViewHolder(private val binding: ItemIngresoBinding) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        fun bind(ingreso: Ingreso) {
            binding.itemIngresoConcepto.text = ingreso.concepto
            binding.itemIngresoFecha.text = dateFormatter.format(Date(ingreso.fecha))
            
            // Formatear el importe como moneda local (Euro en este caso)
            val format = NumberFormat.getCurrencyInstance(Locale.GERMANY) // Usamos Locale.GERMANY para el formato €,##
            binding.itemIngresoImporte.text = format.format(ingreso.importe)
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Ingreso>() {
            override fun areItemsTheSame(oldItem: Ingreso, newItem: Ingreso): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Ingreso, newItem: Ingreso): Boolean {
                return oldItem == newItem
            }
        }
    }
}
