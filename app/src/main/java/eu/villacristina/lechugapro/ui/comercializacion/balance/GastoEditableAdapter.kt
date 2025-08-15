package eu.villacristina.lechugapro.ui.comercializacion.balance

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import eu.villacristina.lechugapro.R
import eu.villacristina.lechugapro.data.Gasto
import java.text.SimpleDateFormat
import java.util.*
import android.app.DatePickerDialog

class GastoEditableAdapter(
    private val onUpdate: (Gasto) -> Unit
) : ListAdapter<Gasto, GastoEditableAdapter.VH>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gasto_editable, parent, false)
        return VH(view, onUpdate)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    class VH(itemView: View, private val onUpdate: (Gasto) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val txtTipo: TextView = itemView.findViewById(R.id.text_tipo_gasto)
        private val editImporte: EditText = itemView.findViewById(R.id.edit_importe_gasto)
        private val editFecha: EditText = itemView.findViewById(R.id.edit_fecha_gasto)
        private val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        private var current: Gasto? = null
        private var ignore = false

        private val importeWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (ignore) return
                val gasto = current ?: return
                val nuevoImporte = s?.toString()?.replace(",", ".")?.toDoubleOrNull() ?: return
                if (nuevoImporte != gasto.importe) onUpdate(gasto.copy(importe = nuevoImporte))
            }
        }

        private val fechaWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (ignore) return
                val gasto = current ?: return
                val texto = s?.toString()?.trim().orEmpty()
                try {
                    val millis = df.parse(texto)?.time ?: return
                    if (millis != gasto.fecha) onUpdate(gasto.copy(fecha = millis))
                } catch (_: Exception) {
                    // formato inválido, ignorar
                }
            }
        }

        fun bind(gasto: Gasto) {
            current = gasto
            ignore = true
            txtTipo.text = gasto.tipo
            editImporte.setText(gasto.importe.toString())
            editFecha.setText(df.format(Date(gasto.fecha)))
            ignore = false

            editImporte.removeTextChangedListener(importeWatcher)
            editFecha.removeTextChangedListener(fechaWatcher)
            editImporte.addTextChangedListener(importeWatcher)
            editFecha.addTextChangedListener(fechaWatcher)

            editFecha.setOnClickListener {
                val cal = Calendar.getInstance().apply { timeInMillis = current?.fecha ?: System.currentTimeMillis() }
                DatePickerDialog(itemView.context,
                    { _, y, m, d ->
                        val pickedCal = Calendar.getInstance().apply { set(y, m, d, 0, 0, 0); set(Calendar.MILLISECOND, 0) }
                        val millis = pickedCal.timeInMillis
                        val gastoSel = current ?: return@DatePickerDialog
                        if (millis != gastoSel.fecha) onUpdate(gastoSel.copy(fecha = millis))
                        editFecha.setText(df.format(pickedCal.time))
                    },
                    cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Gasto>() {
            override fun areItemsTheSame(oldItem: Gasto, newItem: Gasto) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Gasto, newItem: Gasto) = oldItem == newItem
        }
    }
}
