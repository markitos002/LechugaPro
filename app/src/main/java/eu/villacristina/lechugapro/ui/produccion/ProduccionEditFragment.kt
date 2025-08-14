package eu.villacristina.lechugapro.ui.produccion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.google.android.material.textfield.MaterialAutoCompleteTextView
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
import com.google.android.material.textfield.TextInputLayout
import eu.villacristina.lechugapro.notifications.ReminderScheduler

class ProduccionEditFragment : Fragment() {
    private val args: ProduccionEditFragmentArgs by navArgs()

    private val viewModel: ProduccionEditViewModel by viewModels {
        val app = requireActivity().application as LechugaProApplication
        ProduccionEditViewModel.Factory(CicloProduccionRepository(app.database.cicloProduccionDao()), args.cicloId)
    }

    private lateinit var inputNombre: EditText
    private lateinit var inputVariedad: EditText
    private lateinit var inputNumero: EditText
    private lateinit var inputAntifungico: EditText
    private lateinit var inputK1: EditText
    private lateinit var inputK2: EditText
    private lateinit var inputK3: EditText
    private lateinit var inputSiembra: EditText
    private lateinit var inputEstimado: EditText
    private lateinit var inputNotas: EditText
    private lateinit var layoutNumeroCiclo: TextInputLayout

    private var selectedSiembra: Long? = null
    private var selectedEstimado: Long? = null
    private var selectedAntifungico: Long? = null
    private var selectedK1: Long? = null
    private var selectedK2: Long? = null
    private var selectedK3: Long? = null

    private val df = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_produccion_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    inputNombre = view.findViewById(R.id.input_nombre)
    inputVariedad = view.findViewById(R.id.input_variedad)
    inputNumero = view.findViewById(R.id.input_numero_plantas)
    inputAntifungico = view.findViewById(R.id.input_fecha_antifungico)
    inputK1 = view.findViewById(R.id.input_fecha_k1)
    inputK2 = view.findViewById(R.id.input_fecha_k2)
    inputK3 = view.findViewById(R.id.input_fecha_k3)
    inputSiembra = view.findViewById(R.id.input_fecha_siembra)
    inputEstimado = view.findViewById(R.id.input_fecha_estimada)
    inputNotas = view.findViewById(R.id.input_notas)
    layoutNumeroCiclo = view.findViewById(R.id.layout_numero_ciclo)

        // Evitar teclado y usar picker
    listOf(inputSiembra).forEach { et ->
            et.isFocusable = false
            et.isClickable = true
            et.keyListener = null
        }
        // Los campos derivados son solo lectura
    listOf(inputAntifungico, inputK1, inputK2, inputK3, inputEstimado).forEach { et ->
            et.isFocusable = false
            et.isClickable = false
            et.keyListener = null
        }

        (inputVariedad as? MaterialAutoCompleteTextView)?.setOnClickListener {
            (it as MaterialAutoCompleteTextView).showDropDown()
        }

        // Siembra define fechas derivadas
        inputSiembra.setOnClickListener { showDatePicker("Fecha siembra", selectedSiembra) { ts ->
            selectedSiembra = ts
            inputSiembra.setText(ts?.let { df.format(Date(it)) } ?: "")
            recalcDerivedFromSiembra()
        } }
    // inputEstimado ahora es derivado

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
    inputNombre.setText(c.numeroCiclo?.toString())
        inputVariedad.setText(c.variedad)
        inputNumero.setText(c.numeroPlantas.takeIf { it != 0 }?.toString() ?: "")
    selectedSiembra = c.fechaSiembra
    selectedEstimado = c.fechaEstimadaCosecha
    // Derivar antifúngico y potasio desde siembra
    inputSiembra.setText(selectedSiembra?.let { df.format(Date(it)) } ?: "")
    recalcDerivedFromSiembra()
    inputEstimado.setText(selectedEstimado?.let { df.format(Date(it)) } ?: "")
        inputNotas.setText(c.notas)
    }

    private fun parseDate(text: String): Long? = try { if (text.isBlank()) null else df.parse(text)?.time } catch (_: Exception) { null }

    private fun recalcDerivedFromSiembra() {
    val s = selectedSiembra ?: return
    val dayMs = 24L * 60 * 60 * 1000
    selectedAntifungico = s + 5L * dayMs
    selectedK1 = s + 7L * dayMs
    selectedK2 = s + 14L * dayMs
    selectedK3 = s + 21L * dayMs
    selectedEstimado = s + 56L * dayMs
    inputAntifungico.setText(df.format(Date(selectedAntifungico!!)))
    inputK1.setText(df.format(Date(selectedK1!!)))
    inputK2.setText(df.format(Date(selectedK2!!)))
    inputK3.setText(df.format(Date(selectedK3!!)))
    inputEstimado.setText(df.format(Date(selectedEstimado!!)))
    }

    private fun save() {
        val nombre = inputNombre.text.toString().trim()
        layoutNumeroCiclo.error = null
        if (nombre.isEmpty()) {
            layoutNumeroCiclo.error = "Número de ciclo obligatorio"
            return
        }
        val numeroCiclo = nombre.toIntOrNull()
        if (numeroCiclo == null) {
            layoutNumeroCiclo.error = "Número de ciclo inválido"
            return
        }
    val variedad = inputVariedad.text.toString().trim().ifBlank { null }
    val numero = inputNumero.text.toString().toIntOrNull() ?: 0
    val siembra = selectedSiembra ?: parseDate(inputSiembra.text.toString())
    // Recalcular derivados desde siembra si es posible
    if (siembra != null) {
            selectedAntifungico = siembra + 5L * 24 * 60 * 60 * 1000
            selectedK1 = siembra + 7L * 24 * 60 * 60 * 1000
            selectedK2 = siembra + 14L * 24 * 60 * 60 * 1000
            selectedK3 = siembra + 21L * 24 * 60 * 60 * 1000
            selectedEstimado = siembra + 56L * 24 * 60 * 60 * 1000
            inputAntifungico.setText(df.format(Date(selectedAntifungico!!)))
            inputK1.setText(df.format(Date(selectedK1!!)))
            inputK2.setText(df.format(Date(selectedK2!!)))
            inputK3.setText(df.format(Date(selectedK3!!)))
            inputEstimado.setText(df.format(Date(selectedEstimado!!)))
        }
        val estimada = selectedEstimado ?: parseDate(inputEstimado.text.toString())

        // Validaciones básicas
        fun invalid(msg: String): Boolean { android.widget.Toast.makeText(requireContext(), msg, android.widget.Toast.LENGTH_SHORT).show(); return true }
        if (siembra != null && estimada != null && siembra > estimada) {
            if (invalid("Siembra debe ser <= estimada")) return
        }
        val notas = inputNotas.text.toString().ifBlank { null }

        if (args.cicloId == -1L) {
            viewModel.insert(
                CicloProduccion(
                    numeroCiclo = numeroCiclo,
                    variedad = variedad,
                    numeroPlantas = numero,
            fechaInicioPreparacionTierra = null,
            fechaFinPreparacionTierra = null,
            fechaAbono = null,
                    fechaSiembra = siembra,
            fechaSuplementoMinerales = null,
                    fechaEstimadaCosecha = selectedEstimado,
            fechaRealCosecha = null,
                    fechaAntifungico = selectedAntifungico,
                    fechaK1 = selectedK1,
                    fechaK2 = selectedK2,
                    fechaK3 = selectedK3,
                    notas = notas,
                    estado = "Planificado"
                )
        ) { ok, msg, id ->
                if (!ok) {
                    if (msg?.contains("Número de ciclo ya existe") == true) layoutNumeroCiclo.error = msg else Toast.makeText(requireContext(), msg ?: "Error", Toast.LENGTH_SHORT).show()
                } else {
        scheduleReminders(id ?: 0L, siembra)
            findNavController().popBackStack()
                }
            }
        } else {
            viewModel.update(
                CicloProduccion(
                    id = args.cicloId,
                    numeroCiclo = numeroCiclo,
                    variedad = variedad,
                    numeroPlantas = numero,
            fechaInicioPreparacionTierra = null,
            fechaFinPreparacionTierra = null,
            fechaAbono = null,
                    fechaSiembra = siembra,
            fechaSuplementoMinerales = null,
                    fechaEstimadaCosecha = selectedEstimado,
            fechaRealCosecha = null,
                    fechaAntifungico = selectedAntifungico,
                    fechaK1 = selectedK1,
                    fechaK2 = selectedK2,
                    fechaK3 = selectedK3,
                    notas = notas,
                    estado = viewModel.ciclo.value?.estado ?: "Planificado"
                )
        ) { ok, msg ->
                if (!ok) {
                    if (msg?.contains("Número de ciclo ya existe") == true) layoutNumeroCiclo.error = msg else Toast.makeText(requireContext(), msg ?: "Error", Toast.LENGTH_SHORT).show()
                } else {
            ReminderScheduler.cancelCycle(requireContext(), args.cicloId)
        scheduleReminders(args.cicloId, siembra)
                    findNavController().popBackStack()
                }
            }
        }
    }
}

private fun ProduccionEditFragment.scheduleReminders(cicloId: Long, siembra: Long?) {
    val now = System.currentTimeMillis()
    val tag = ReminderScheduler.tagForCycle(cicloId)
    // Fecha estimada de cosecha: 8 semanas (56 días) después de siembra
    siembra?.let {
        val ochoSemanas = 56L * 24 * 60 * 60 * 1000
        val delay = it + ochoSemanas - now
        if (delay > 0) ReminderScheduler.schedule(requireContext(), delay, "Cosecha estimada", "Revisa cosecha ciclo #$cicloId", (cicloId * 10 + 2).toInt(), tag)
    // Antifúngico: 5 días después de siembra
    val cincoDias = 5L * 24 * 60 * 60 * 1000
    val dAnti = it + cincoDias - now
    if (dAnti > 0) ReminderScheduler.schedule(requireContext(), dAnti, "Antifúngico", "Aplicación antifúngico ciclo #$cicloId", (cicloId * 10 + 3).toInt(), tag)
    // Potasio K: 7, 14 y 21 días después de siembra
    val siete = 7L * 24 * 60 * 60 * 1000
    val catorce = 14L * 24 * 60 * 60 * 1000
    val veintiuno = 21L * 24 * 60 * 60 * 1000
    val k1 = it + siete - now
    if (k1 > 0) ReminderScheduler.schedule(requireContext(), k1, "Potasio (K)", "1/3 aplicación ciclo #$cicloId", (cicloId * 10 + 4).toInt(), tag)
    val k2 = it + catorce - now
    if (k2 > 0) ReminderScheduler.schedule(requireContext(), k2, "Potasio (K)", "2/3 aplicación ciclo #$cicloId", (cicloId * 10 + 5).toInt(), tag)
    val k3 = it + veintiuno - now
    if (k3 > 0) ReminderScheduler.schedule(requireContext(), k3, "Potasio (K)", "3/3 aplicación ciclo #$cicloId", (cicloId * 10 + 6).toInt(), tag)
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

    fun insert(c: CicloProduccion, onComplete: (Boolean, String?, Long?) -> Unit = { _, _, _ -> }) {
        viewModelScope.launch {
            val num = c.numeroCiclo
            if (num != null && repository.existeNumeroCiclo(num, null)) {
                onComplete(false, "Número de ciclo ya existe", null)
            } else {
                val id = repository.insert(c)
                onComplete(true, null, id)
            }
        }
    }

    fun update(c: CicloProduccion, onComplete: (Boolean, String?) -> Unit = { _, _ -> }) {
        viewModelScope.launch {
            val num = c.numeroCiclo
            val currentId = c.id.takeIf { it != 0L }
            if (num != null && repository.existeNumeroCiclo(num, currentId)) {
                onComplete(false, "Número de ciclo ya existe")
            } else {
                repository.update(c)
                onComplete(true, null)
            }
        }
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
