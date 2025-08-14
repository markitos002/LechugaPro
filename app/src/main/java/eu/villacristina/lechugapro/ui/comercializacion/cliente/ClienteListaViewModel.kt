package eu.villacristina.lechugapro.ui.comercializacion.cliente

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eu.villacristina.lechugapro.data.Cliente
import eu.villacristina.lechugapro.data.ClienteRepository
import eu.villacristina.lechugapro.data.IngresoRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ClienteListaViewModel(
    repository: ClienteRepository,
    ingresoRepository: IngresoRepository
) : ViewModel() {

    val todosLosClientes: StateFlow<List<Cliente>> = repository.todosLosClientes
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Total de ingresos sumados de todos los clientes
    val totalIngresos: StateFlow<Double> = ingresoRepository.todosLosIngresos
        .map { lista -> lista.sumOf { it.importe } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )
}

class ClienteListaViewModelFactory(
    private val repository: ClienteRepository,
    private val ingresoRepository: IngresoRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClienteListaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ClienteListaViewModel(repository, ingresoRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
