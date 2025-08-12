package eu.villacristina.lechugapro.ui.comercializacion.ingreso

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eu.villacristina.lechugapro.data.Ingreso
import eu.villacristina.lechugapro.data.IngresoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class IngresoEditViewModel(
    private val repository: IngresoRepository,
    val clienteId: Long, // Este es el ID que recibimos, está bien que se llame así aquí
    private val ingresoId: Long
) : ViewModel() {

    private val _ingreso = MutableStateFlow<Ingreso?>(null)
    val ingreso: StateFlow<Ingreso?> = _ingreso.asStateFlow()

    init {
        if (ingresoId != -1L) { // -1L indica que es un nuevo ingreso
            viewModelScope.launch {
                _ingreso.value = repository.getIngresoById(ingresoId).first()
            }
        }
    }

    fun guardarIngreso(fecha: Long, concepto: String, importe: Double, notas: String) {
        val ingresoActual = _ingreso.value
        val fechaComoString = fecha.toString()

        if (ingresoActual != null) { // Estamos editando
            val ingresoActualizado = ingresoActual.copy(
                fecha = fechaComoString,
                concepto = concepto,
                importe = importe,
                notas = notas
            )
            update(ingresoActualizado)
        } else { // Estamos creando
            // --- CORRECCIÓN FINAL AQUÍ ---
            // El parámetro en el constructor de Ingreso se llama "idCliente"
            val nuevoIngreso = Ingreso(
                idCliente = clienteId, // Usamos el nombre de parámetro correcto
                fecha = fechaComoString,
                concepto = concepto,
                importe = importe,
                notas = notas
            )
            insert(nuevoIngreso)
        }
    }

    private fun insert(ingreso: Ingreso) = viewModelScope.launch {
        repository.insert(ingreso)
    }

    private fun update(ingreso: Ingreso) = viewModelScope.launch {
        repository.update(ingreso)
    }
}

class IngresoEditViewModelFactory(
    private val repository: IngresoRepository,
    private val clienteId: Long,
    private val ingresoId: Long
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IngresoEditViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return IngresoEditViewModel(repository, clienteId, ingresoId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}