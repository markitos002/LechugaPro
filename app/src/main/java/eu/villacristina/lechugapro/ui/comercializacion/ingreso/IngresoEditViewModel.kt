package eu.villacristina.lechugapro.ui.comercializacion.ingreso

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eu.villacristina.lechugapro.data.Ingreso
import eu.villacristina.lechugapro.data.IngresoRepository
import eu.villacristina.lechugapro.data.IngresoRepositoryContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class IngresoEditViewModel(
    private val repository: IngresoRepositoryContract,
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

    fun guardarIngreso(fechaMillis: Long, concepto: String, importe: Double, notas: String) {
        val ingresoActual = _ingreso.value
        if (ingresoActual != null) { // Editar
            val ingresoActualizado = ingresoActual.copy(
                fecha = fechaMillis,
                concepto = concepto,
                importe = importe,
                notas = notas
            )
            update(ingresoActualizado)
        } else { // Crear
            val nuevoIngreso = Ingreso(
                idCliente = clienteId,
                fecha = fechaMillis,
                concepto = concepto,
                importe = importe,
                notas = notas.ifBlank { null }
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
    private val repository: IngresoRepositoryContract,
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