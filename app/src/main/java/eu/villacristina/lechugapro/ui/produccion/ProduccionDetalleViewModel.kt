package eu.villacristina.lechugapro.ui.produccion

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eu.villacristina.lechugapro.data.CicloProduccion
import eu.villacristina.lechugapro.data.CicloProduccionRepository
import kotlinx.coroutines.launch

class ProduccionDetalleViewModel(
    private val repository: CicloProduccionRepository,
    private val cicloId: Long
) : ViewModel() {

    // 1. Exponer el LiveData directamente desde el repositorio.
    // El init y el StateFlow ya no son necesarios.
    val ciclo: LiveData<CicloProduccion?> = repository.obtenerCicloPorId(cicloId)

    /**
     *  FUNCIÓN ACTUALIZADA: Actualiza el estado del ciclo actual en la base de datos.
     */
    fun cambiarEstadoCiclo(nuevoEstado: String) {
        viewModelScope.launch {
            // 2. Obtener el valor actual del LiveData.
            // El observador en la UI se asegurará de que esto no sea nulo cuando se llame.
            val cicloActual = ciclo.value
            if (cicloActual != null) {
                // Creamos una copia del ciclo con el estado modificado
                val cicloActualizado = cicloActual.copy(estado = nuevoEstado)
                // Llamamos al repositorio para que lo guarde en la BD
                repository.update(cicloActualizado)
            }
        }
    }
}

class ProduccionDetalleViewModelFactory(
    private val repository: CicloProduccionRepository,
    private val cicloId: Long
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProduccionDetalleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProduccionDetalleViewModel(repository, cicloId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}