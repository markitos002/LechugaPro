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
    private lateinit var inputInicioPrep: EditText
    private lateinit var inputFinPrep: EditText
    private lateinit var inputAbono: EditText
    private lateinit var inputSiembra: EditText
    private lateinit var inputEstimado: EditText
    private lateinit var inputReal: EditText
    private lateinit var inputNotas: EditText
    private lateinit var layoutNumeroCiclo: TextInputLayout

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
    inputSiembra = view.findViewById(R.id.input_fecha_siembra)
    inputEstimado = view.findViewById(R.id.input_fecha_estimada)
    inputReal = view.findViewById(R.id.input_fecha_real_cosecha)
    inputNotas = view.findViewById(R.id.input_notas)
    layoutNumeroCiclo = view.findViewById(R.id.layout_numero_ciclo)

        // Evitar teclado y usar picker
        listOf(inputSiembra, inputAbono, inputReal).forEach { et ->
            et.isFocusable = false
            et.isClickable = true
            et.keyListener = null
        }
        // Los campos derivados son solo lectura
        listOf(inputInicioPrep, inputFinPrep, inputEstimado).forEach { et ->
            et.isFocusable = false
            et.isClickable = false
            et.keyListener = null
        }

        (inputVariedad as? MaterialAutoCompleteTextView)?.setOnClickListener {
            (it as MaterialAutoCompleteTextView).showDropDown()
        }

        // Siembra define fechas derivadas
        inputAbono.setOnClickListener { showDatePicker("Fecha abono", selectedAbono) { ts ->
            selectedAbono = ts
            inputAbono.setText(ts?.let { df.format(Date(it)) } ?: "")
        } }
        inputSiembra.setOnClickListener { showDatePicker("Fecha siembra", selectedSiembra) { ts ->
            selectedSiembra = ts
            inputSiembra.setText(ts?.let { df.format(Date(it)) } ?: "")
            recalcDerivedFromSiembra()
        } }
        // inputEstimado ahora es derivado
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
    inputNombre.setText(c.numeroCiclo?.toString())
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
    // Campo de suplemento removido del formulario; valor se conserva internamente si existe
    inputSiembra.setText(selectedSiembra?.let { df.format(Date(it)) } ?: "")
    inputEstimado.setText(selectedEstimado?.let { df.format(Date(it)) } ?: "")
    inputReal.setText(selectedReal?.let { df.format(Date(it)) } ?: "")
        inputNotas.setText(c.notas)
    }

    private fun parseDate(text: String): Long? = try { if (text.isBlank()) null else df.parse(text)?.time } catch (_: Exception) { null }

    private fun recalcDerivedFromSiembra() {
        val s = selectedSiembra ?: return
        val dayMs = 24L * 60 * 60 * 1000
        selectedInicioPrep = s - 3L * dayMs
        selectedFinPrep = s - 1L * dayMs
        selectedEstimado = s + 56L * dayMs
        inputInicioPrep.setText(df.format(Date(selectedInicioPrep!!)))
        inputFinPrep.setText(df.format(Date(selectedFinPrep!!)))
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
            val tresDias = 3L * 24 * 60 * 60 * 1000
            selectedInicioPrep = siembra - tresDias
            selectedFinPrep = siembra - 1L * 24 * 60 * 60 * 1000
            selectedEstimado = siembra + 56L * 24 * 60 * 60 * 1000
            inputInicioPrep.setText(df.format(Date(selectedInicioPrep!!)))
            inputFinPrep.setText(df.format(Date(selectedFinPrep!!)))
            inputEstimado.setText(df.format(Date(selectedEstimado!!)))
        }
        val inicioPrep = selectedInicioPrep
        val finPrep = selectedFinPrep
        val abono = selectedAbono ?: parseDate(inputAbono.text.toString())
    val suplemento = selectedSuplemento
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
                    numeroCiclo = numeroCiclo,
                    variedad = variedad,
                    numeroPlantas = numero,
                    fechaInicioPreparacionTierra = inicioPrep,
                    fechaFinPreparacionTierra = finPrep,
                    fechaAbono = abono,
                    fechaSiembra = siembra,
                    fechaSuplementoMinerales = suplemento,
                    fechaEstimadaCosecha = selectedEstimado,
                    fechaRealCosecha = real,
                    notas = notas,
                    estado = "Planificado"
                )
        ) { ok, msg, id ->
                if (!ok) {
                    if (msg?.contains("Número de ciclo ya existe") == true) layoutNumeroCiclo.error = msg else Toast.makeText(requireContext(), msg ?: "Error", Toast.LENGTH_SHORT).show()
                } else {
            scheduleReminders(id ?: 0L, siembra, inicioPrep)
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
                    fechaInicioPreparacionTierra = inicioPrep,
                    fechaFinPreparacionTierra = finPrep,
                    fechaAbono = abono,
                    fechaSiembra = siembra,
                    fechaSuplementoMinerales = suplemento,
                    fechaEstimadaCosecha = selectedEstimado,
                    fechaRealCosecha = real,
                    notas = notas,
                    estado = viewModel.ciclo.value?.estado ?: "Planificado"
                )
        ) { ok, msg ->
                if (!ok) {
                    if (msg?.contains("Número de ciclo ya existe") == true) layoutNumeroCiclo.error = msg else Toast.makeText(requireContext(), msg ?: "Error", Toast.LENGTH_SHORT).show()
                } else {
            ReminderScheduler.cancelCycle(requireContext(), args.cicloId)
            scheduleReminders(args.cicloId, siembra, inicioPrep)
                    findNavController().popBackStack()
                }
            }
        }
    }
}

private fun ProduccionEditFragment.scheduleReminders(cicloId: Long, siembra: Long?, inicioPrep: Long?) {
    val now = System.currentTimeMillis()
    val tag = ReminderScheduler.tagForCycle(cicloId)
    // Recordatorio de preparación de terreno (si hay fecha de inicio y está en el futuro)
    inicioPrep?.let {
        val delay = it - now
        if (delay > 0) ReminderScheduler.schedule(requireContext(), delay, "Preparación de terreno", "Inicia preparación del ciclo #$cicloId", (cicloId * 10 + 1).toInt(), tag)
    }
    // Fecha estimada de cosecha: 8 semanas (56 días) después de siembra
    siembra?.let {
        val ochoSemanas = 56L * 24 * 60 * 60 * 1000
        val delay = it + ochoSemanas - now
        if (delay > 0) ReminderScheduler.schedule(requireContext(), delay, "Cosecha estimada", "Revisa cosecha ciclo #$cicloId", (cicloId * 10 + 2).toInt(), tag)
        // Suplemento de potasio: 3 veces, cada semana tras la siembra
        val unaSemana = 7L * 24 * 60 * 60 * 1000
        for (i in 1..3) {
            val whenTs = it + i * unaSemana
            val d = whenTs - now
            if (d > 0) ReminderScheduler.schedule(requireContext(), d, "Suplemento de potasio", "Aplicación ${i}/3 ciclo #$cicloId", (cicloId * 10 + 2 + i).toInt(), tag)
        }
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
