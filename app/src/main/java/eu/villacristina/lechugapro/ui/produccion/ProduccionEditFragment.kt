package eu.villacristina.lechugapro.ui.produccion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import eu.villacristina.lechugapro.LechugaProApplication
import eu.villacristina.lechugapro.R
import eu.villacristina.lechugapro.data.CicloProduccion
import eu.villacristina.lechugapro.data.CicloProduccionRepository
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.CalendarConstraints

class ProduccionEditFragment : Fragment() {
    private val args: ProduccionEditFragmentArgs by navArgs()

    private val viewModel: ProduccionEditViewModel by viewModels {
        val app = requireActivity().application as LechugaProApplication
        ProduccionEditViewModel.Factory(CicloProduccionRepository(app.database.cicloProduccionDao()), args.cicloId)
    }

    private lateinit var inputNombre: EditText
    private lateinit var inputVariedad: EditText
    private lateinit var inputNumero: EditText
    private lateinit var inputSiembra: EditText
    private lateinit var inputEstimado: EditText
    private lateinit var inputNotas: EditText

    private var selectedSiembra: Long? = null
    private var selectedEstimado: Long? = null

    private val df = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_produccion_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inputNombre = view.findViewById(R.id.input_nombre)
        inputVariedad = view.findViewById(R.id.input_variedad)
        inputNumero = view.findViewById(R.id.input_numero_plantas)
        inputSiembra = view.findViewById(R.id.input_fecha_siembra)
        inputEstimado = view.findViewById(R.id.input_fecha_estimada)
        inputNotas = view.findViewById(R.id.input_notas)

        // Evitar teclado y usar picker
        listOf(inputSiembra, inputEstimado).forEach { et ->
            et.isFocusable = false
            et.isClickable = true
            et.keyListener = null
        }

        inputSiembra.setOnClickListener { showDatePicker("Fecha siembra", selectedSiembra) { ts ->
            selectedSiembra = ts
            inputSiembra.setText(ts?.let { df.format(Date(it)) } ?: "")
        } }
        inputEstimado.setOnClickListener { showDatePicker("Fecha estimada cosecha", selectedEstimado) { ts ->
            selectedEstimado = ts
            inputEstimado.setText(ts?.let { df.format(Date(it)) } ?: "")
        } }

        if (args.cicloId != -1L) {
            viewModel.ciclo.observe(viewLifecycleOwner) { c ->
                c?.let { bind(it) }
            }
        }

        view.findViewById<View>(R.id.button_guardar).setOnClickListener {
            save()
        }
    }

    private fun bind(c: CicloProduccion) {
        inputNombre.setText(c.nombreCiclo)
        inputVariedad.setText(c.variedad)
        inputNumero.setText(c.numeroPlantas.takeIf { it != 0 }?.toString() ?: "")
    selectedSiembra = c.fechaSiembra
    selectedEstimado = c.fechaEstimadaCosecha
    inputSiembra.setText(selectedSiembra?.let { df.format(Date(it)) } ?: "")
    inputEstimado.setText(selectedEstimado?.let { df.format(Date(it)) } ?: "")
        inputNotas.setText(c.notas)
    }

    private fun parseDate(text: String): Long? = try { if (text.isBlank()) null else df.parse(text)?.time } catch (_: Exception) { null }

    private fun save() {
        val nombre = inputNombre.text.toString().trim()
        if (nombre.isEmpty()) {
            Toast.makeText(requireContext(), "Nombre obligatorio", Toast.LENGTH_SHORT).show(); return
        }
        val variedad = inputVariedad.text.toString().trim().ifBlank { null }
        val numero = inputNumero.text.toString().toIntOrNull() ?: 0
    val siembra = selectedSiembra ?: parseDate(inputSiembra.text.toString())
    val estimada = selectedEstimado ?: parseDate(inputEstimado.text.toString())
        val notas = inputNotas.text.toString().ifBlank { null }

        if (args.cicloId == -1L) {
            viewModel.insert(
                CicloProduccion(
                    nombreCiclo = nombre,
                    variedad = variedad,
                    numeroPlantas = numero,
                    fechaSiembra = siembra,
                    fechaEstimadaCosecha = estimada,
                    notas = notas,
                    estado = "Planificado"
                )
            )
        } else {
            viewModel.update(
                CicloProduccion(
                    id = args.cicloId,
                    nombreCiclo = nombre,
                    variedad = variedad,
                    numeroPlantas = numero,
                    fechaSiembra = siembra,
                    fechaEstimadaCosecha = estimada,
                    notas = notas,
                    estado = viewModel.ciclo.value?.estado ?: "Planificado"
                )
            )
        }
        findNavController().popBackStack()
    }
}

private fun ProduccionEditFragment.showDatePicker(title: String, current: Long?, onSelected: (Long?) -> Unit) {
    val builder = MaterialDatePicker.Builder.datePicker()
        .setTitleText(title)
    current?.let { builder.setSelection(it) }
    val picker = builder.build()
    picker.addOnPositiveButtonClickListener { sel ->
        onSelected(sel as? Long)
    }
    picker.addOnNegativeButtonClickListener { /* ignored */ }
    picker.addOnCancelListener { /* ignored */ }
    picker.show(parentFragmentManager, title.replace(" ", "_"))
}

class ProduccionEditViewModel(private val repository: CicloProduccionRepository, cicloId: Long) : ViewModel() {
    val ciclo = repository.obtenerCicloPorId(cicloId)

    fun insert(c: CicloProduccion) {
        viewModelScope.launch { repository.insert(c) }
    }

    fun update(c: CicloProduccion) {
        viewModelScope.launch { repository.update(c) }
    }

    class Factory(private val repo: CicloProduccionRepository, private val id: Long) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProduccionEditViewModel::class.java)) {
                return ProduccionEditViewModel(repo, id) as T
            }
            throw IllegalArgumentException("Unknown VM class")
        }
    }
}
