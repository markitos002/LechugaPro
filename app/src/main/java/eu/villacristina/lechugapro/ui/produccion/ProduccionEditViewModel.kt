package eu.villacristina.lechugapro.ui.produccion

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eu.villacristina.lechugapro.data.CicloProduccion
import eu.villacristina.lechugapro.data.CicloProduccionRepository
import kotlinx.coroutines.launch

class ProduccionEditViewModel(
    private val repository: CicloProduccionRepository,
    cicloId: Long // El ID ahora se recibe en el constructor
) : ViewModel() {

    // El LiveData del ciclo se expone directamente para que el Fragment lo observe.
    // Se inicializa una vez con el ID proporcionado.
    val ciclo: LiveData<CicloProduccion?> = repository.obtenerCicloPorId(cicloId)

    /**
     * Lanza una corrutina para insertar un nuevo ciclo en la base de datos.
     */
    fun insert(ciclo: CicloProduccion) = viewModelScope.launch {
        repository.insert(ciclo)
    }

    /**
     * Lanza una corrutina para actualizar un ciclo existente.
     */
    fun update(ciclo: CicloProduccion) = viewModelScope.launch {
        repository.update(ciclo)
    }
}

/**
 * Factory para crear una instancia de ProduccionEditViewModel.
 * Ahora acepta tanto el repositorio como el ID del ciclo.
 */
class ProduccionEditViewModelFactory(
    private val repository: CicloProduccionRepository,
    private val cicloId: Long
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProduccionEditViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            // Pasa ambos parámetros al constructor del ViewModel.
            return ProduccionEditViewModel(repository, cicloId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
