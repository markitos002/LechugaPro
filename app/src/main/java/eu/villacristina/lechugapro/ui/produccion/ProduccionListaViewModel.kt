package eu.villacristina.lechugapro.ui.produccion

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.villacristina.lechugapro.data.CicloProduccion
import eu.villacristina.lechugapro.data.CicloProduccionRepository

class ProduccionListaViewModel(repository: CicloProduccionRepository) : ViewModel() {

    // Simplemente expone el LiveData del repositorio directamente.
    // No es necesario convertirlo, ya que el DAO lo proporciona como LiveData.
    val todosLosCiclos: LiveData<List<CicloProduccion>> = repository.todosLosCiclos
}

class ProduccionListaViewModelFactory(private val repository: CicloProduccionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProduccionListaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProduccionListaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
