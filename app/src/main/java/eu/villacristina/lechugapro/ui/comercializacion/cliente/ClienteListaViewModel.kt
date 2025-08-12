package eu.villacristina.lechugapro.ui.comercializacion.cliente

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eu.villacristina.lechugapro.data.Cliente
import eu.villacristina.lechugapro.data.ClienteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ClienteListaViewModel(repository: ClienteRepository) : ViewModel() {

    val todosLosClientes: StateFlow<List<Cliente>> = repository.todosLosClientes
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}

class ClienteListaViewModelFactory(private val repository: ClienteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClienteListaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ClienteListaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
