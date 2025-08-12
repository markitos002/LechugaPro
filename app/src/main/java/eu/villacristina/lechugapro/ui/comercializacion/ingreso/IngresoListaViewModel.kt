package eu.villacristina.lechugapro.ui.comercializacion.ingreso

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eu.villacristina.lechugapro.data.Ingreso
import eu.villacristina.lechugapro.data.IngresoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class IngresoListaViewModel(repository: IngresoRepository) : ViewModel() {

    val todosLosIngresos: StateFlow<List<Ingreso>> = repository.todosLosIngresos
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}

class IngresoListaViewModelFactory(private val repository: IngresoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IngresoListaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return IngresoListaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
