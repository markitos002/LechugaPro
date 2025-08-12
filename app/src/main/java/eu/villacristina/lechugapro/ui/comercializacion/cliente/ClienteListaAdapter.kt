package eu.villacristina.lechugapro.ui.comercializacion.cliente

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.villacristina.lechugapro.data.Cliente
import eu.villacristina.lechugapro.databinding.ItemClienteBinding

class ClienteListaAdapter(private val onItemClicked: (Cliente) -> Unit) :
    ListAdapter<Cliente, ClienteListaAdapter.ClienteViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        val binding = ItemClienteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClienteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

    class ClienteViewHolder(private val binding: ItemClienteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cliente: Cliente) {
            binding.itemClienteNombre.text = cliente.nombreCompleto
            binding.itemClienteTelefono.text = cliente.telefono ?: "Sin teléfono"
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Cliente>() {
            override fun areItemsTheSame(oldItem: Cliente, newItem: Cliente): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Cliente, newItem: Cliente): Boolean {
                return oldItem == newItem
            }
        }
    }
}
