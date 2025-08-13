package eu.villacristina.lechugapro.ui.comercializacion.ingreso

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eu.villacristina.lechugapro.data.Ingreso
import eu.villacristina.lechugapro.data.IngresoRepository
import eu.villacristina.lechugapro.data.IngresoRepositoryContract
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class IngresoListaViewModel(private val repository: IngresoRepositoryContract) : ViewModel() {

    val todosLosIngresos: StateFlow<List<Ingreso>> = repository.todosLosIngresos
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun deleteIngreso(ingreso: Ingreso) = viewModelScope.launch {
        repository.delete(ingreso)
    }

    fun reInsertIngreso(ingreso: Ingreso) = viewModelScope.launch {
        repository.insert(ingreso)
    }
}

class IngresoListaViewModelFactory(private val repository: IngresoRepositoryContract) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IngresoListaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return IngresoListaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
