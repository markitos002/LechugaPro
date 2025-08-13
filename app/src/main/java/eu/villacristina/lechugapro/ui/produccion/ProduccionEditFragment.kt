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

class ProduccionEditFragment : Fragment() {
    private val args: ProduccionEditFragmentArgs by navArgs()

    private val viewModel: ProduccionEditViewModel by viewModels {
        val app = requireActivity().application as LechugaProApplication
        ProduccionEditViewModel.Factory(CicloProduccionRepository(app.database.cicloProduccionDao()), args.cicloId)
    }

    private lateinit var inputNombre: EditText
    private lateinit var inputVariedad: EditText
    private lateinit var inputNumero: EditText
    private lateinit var inputInicioPrep: EditText
    private lateinit var inputFinPrep: EditText
    private lateinit var inputAbono: EditText
    private lateinit var inputSuplemento: EditText
    private lateinit var inputSiembra: EditText
    private lateinit var inputEstimado: EditText
    private lateinit var inputReal: EditText
    private lateinit var inputNotas: EditText

    private var selectedSiembra: Long? = null
    private var selectedEstimado: Long? = null
    private var selectedInicioPrep: Long? = null
    private var selectedFinPrep: Long? = null
    private var selectedAbono: Long? = null
    private var selectedSuplemento: Long? = null
    private var selectedReal: Long? = null

    private val df = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_produccion_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    inputNombre = view.findViewById(R.id.input_nombre)
    inputVariedad = view.findViewById(R.id.input_variedad)
    inputNumero = view.findViewById(R.id.input_numero_plantas)
    inputInicioPrep = view.findViewById(R.id.input_fecha_inicio_prep)
    inputFinPrep = view.findViewById(R.id.input_fecha_fin_prep)
    inputAbono = view.findViewById(R.id.input_fecha_abono)
    inputSuplemento = view.findViewById(R.id.input_fecha_suplemento)
    inputSiembra = view.findViewById(R.id.input_fecha_siembra)
    inputEstimado = view.findViewById(R.id.input_fecha_estimada)
    inputReal = view.findViewById(R.id.input_fecha_real_cosecha)
    inputNotas = view.findViewById(R.id.input_notas)

        // Evitar teclado y usar picker
        listOf(inputInicioPrep, inputFinPrep, inputAbono, inputSuplemento, inputSiembra, inputEstimado, inputReal).forEach { et ->
            et.isFocusable = false
            et.isClickable = true
            et.keyListener = null
        }

        inputInicioPrep.setOnClickListener { showDatePicker("Inicio preparación tierra", selectedInicioPrep) { ts ->
            selectedInicioPrep = ts
            inputInicioPrep.setText(ts?.let { df.format(Date(it)) } ?: "")
        } }
        inputFinPrep.setOnClickListener { showDatePicker("Fin preparación tierra", selectedFinPrep) { ts ->
            selectedFinPrep = ts
            inputFinPrep.setText(ts?.let { df.format(Date(it)) } ?: "")
        } }
        inputAbono.setOnClickListener { showDatePicker("Fecha abono", selectedAbono) { ts ->
            selectedAbono = ts
            inputAbono.setText(ts?.let { df.format(Date(it)) } ?: "")
        } }
        inputSuplemento.setOnClickListener { showDatePicker("Fecha suplemento minerales", selectedSuplemento) { ts ->
            selectedSuplemento = ts
            inputSuplemento.setText(ts?.let { df.format(Date(it)) } ?: "")
        } }
        inputSiembra.setOnClickListener { showDatePicker("Fecha siembra", selectedSiembra) { ts ->
            selectedSiembra = ts
            inputSiembra.setText(ts?.let { df.format(Date(it)) } ?: "")
        } }
        inputEstimado.setOnClickListener { showDatePicker("Fecha estimada cosecha", selectedEstimado) { ts ->
            selectedEstimado = ts
            inputEstimado.setText(ts?.let { df.format(Date(it)) } ?: "")
        } }
        inputReal.setOnClickListener { showDatePicker("Fecha real de cosecha", selectedReal) { ts ->
            selectedReal = ts
            inputReal.setText(ts?.let { df.format(Date(it)) } ?: "")
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
    selectedInicioPrep = c.fechaInicioPreparacionTierra
    selectedFinPrep = c.fechaFinPreparacionTierra
    selectedAbono = c.fechaAbono
    selectedSuplemento = c.fechaSuplementoMinerales
    selectedSiembra = c.fechaSiembra
    selectedEstimado = c.fechaEstimadaCosecha
    selectedReal = c.fechaRealCosecha
    inputInicioPrep.setText(selectedInicioPrep?.let { df.format(Date(it)) } ?: "")
    inputFinPrep.setText(selectedFinPrep?.let { df.format(Date(it)) } ?: "")
    inputAbono.setText(selectedAbono?.let { df.format(Date(it)) } ?: "")
    inputSuplemento.setText(selectedSuplemento?.let { df.format(Date(it)) } ?: "")
    inputSiembra.setText(selectedSiembra?.let { df.format(Date(it)) } ?: "")
    inputEstimado.setText(selectedEstimado?.let { df.format(Date(it)) } ?: "")
    inputReal.setText(selectedReal?.let { df.format(Date(it)) } ?: "")
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
        val inicioPrep = selectedInicioPrep ?: parseDate(inputInicioPrep.text.toString())
        val finPrep = selectedFinPrep ?: parseDate(inputFinPrep.text.toString())
        val abono = selectedAbono ?: parseDate(inputAbono.text.toString())
        val suplemento = selectedSuplemento ?: parseDate(inputSuplemento.text.toString())
        val siembra = selectedSiembra ?: parseDate(inputSiembra.text.toString())
        val estimada = selectedEstimado ?: parseDate(inputEstimado.text.toString())
        val real = selectedReal ?: parseDate(inputReal.text.toString())

        // Validaciones de secuencia básica
        fun invalid(msg: String): Boolean { android.widget.Toast.makeText(requireContext(), msg, android.widget.Toast.LENGTH_SHORT).show(); return true }
        if (inicioPrep != null && finPrep != null && inicioPrep > finPrep) {
            if (invalid("Inicio preparación debe ser <= fin")) return
        }
        if (finPrep != null && siembra != null && finPrep > siembra) {
            if (invalid("Fin preparación debe ser <= siembra")) return
        }
        if (siembra != null && estimada != null && siembra > estimada) {
            if (invalid("Siembra debe ser <= estimada")) return
        }
        if (estimada != null && real != null && estimada > real) {
            if (invalid("Estimada debe ser <= real")) return
        }
        val notas = inputNotas.text.toString().ifBlank { null }

        if (args.cicloId == -1L) {
            viewModel.insert(
                CicloProduccion(
                    nombreCiclo = nombre,
                    variedad = variedad,
                    numeroPlantas = numero,
                    fechaInicioPreparacionTierra = inicioPrep,
                    fechaFinPreparacionTierra = finPrep,
                    fechaAbono = abono,
                    fechaSiembra = siembra,
                    fechaSuplementoMinerales = suplemento,
                    fechaEstimadaCosecha = estimada,
                    fechaRealCosecha = real,
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
                    fechaInicioPreparacionTierra = inicioPrep,
                    fechaFinPreparacionTierra = finPrep,
                    fechaAbono = abono,
                    fechaSiembra = siembra,
                    fechaSuplementoMinerales = suplemento,
                    fechaEstimadaCosecha = estimada,
                    fechaRealCosecha = real,
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
    val picker: MaterialDatePicker<Long> = builder.build()
    picker.addOnPositiveButtonClickListener { selection: Long ->
        onSelected(selection)
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
