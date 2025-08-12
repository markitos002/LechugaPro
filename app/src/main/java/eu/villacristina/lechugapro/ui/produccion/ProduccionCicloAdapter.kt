package eu.villacristina.lechugapro.ui.produccion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.villacristina.lechugapro.R
import eu.villacristina.lechugapro.data.CicloProduccion
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProduccionCicloAdapter(private val onClick: (CicloProduccion) -> Unit) :
    ListAdapter<CicloProduccion, ProduccionCicloAdapter.ProduccionCicloViewHolder>(CicloProduccionDiffCallback) {

    class ProduccionCicloViewHolder(itemView: View, val onClick: (CicloProduccion) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.ciclo_item_nombre)
        private val estadoTextView: TextView = itemView.findViewById(R.id.ciclo_item_estado)
        private val fechaTextView: TextView = itemView.findViewById(R.id.ciclo_item_fecha)
        private var currentCiclo: CicloProduccion? = null

        init {
            itemView.setOnClickListener {
                currentCiclo?.let {
                    onClick(it)
                }
            }
        }

        fun bind(ciclo: CicloProduccion) {
            currentCiclo = ciclo
            nombreTextView.text = ciclo.nombreCiclo
            estadoTextView.text = ciclo.estado // <-- AÑADIDO

            // Formateo de fecha
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val fechaFormateada = ciclo.fechaSiembra?.let { dateFormat.format(Date(it)) } ?: "Sin fecha"
            fechaTextView.text = "Siembra: $fechaFormateada"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProduccionCicloViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ciclo_produccion, parent, false)
        return ProduccionCicloViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ProduccionCicloViewHolder, position: Int) {
        val ciclo = getItem(position)
        holder.bind(ciclo)
    }
}

object CicloProduccionDiffCallback : DiffUtil.ItemCallback<CicloProduccion>() {
    override fun areItemsTheSame(oldItem: CicloProduccion, newItem: CicloProduccion): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CicloProduccion, newItem: CicloProduccion): Boolean {
        return oldItem == newItem
    }
}
