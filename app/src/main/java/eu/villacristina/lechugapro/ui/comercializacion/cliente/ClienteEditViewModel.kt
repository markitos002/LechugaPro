package eu.villacristina.lechugapro.ui.comercializacion.cliente

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eu.villacristina.lechugapro.data.Cliente
import eu.villacristina.lechugapro.data.ClienteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ClienteEditViewModel(
    private val repository: ClienteRepository,
    private val clienteId: Long
) : ViewModel() {

    // Expone el cliente actual para que la UI lo observe y rellene los campos
    private val _cliente = MutableStateFlow<Cliente?>(null)
    val cliente: StateFlow<Cliente?> = _cliente.asStateFlow()

    init {
        // Si el clienteId no es el valor por defecto, estamos editando. Lo cargamos.
        if (clienteId != -1L) {
            viewModelScope.launch {
                _cliente.value = repository.getClienteById(clienteId).first()
            }
        }
    }

    fun insert(cliente: Cliente) = viewModelScope.launch {
        repository.insert(cliente)
    }

    fun update(cliente: Cliente) = viewModelScope.launch {
        repository.update(cliente)
    }
}

// El Factory ahora necesita también el clienteId para pasárselo al ViewModel
class ClienteEditViewModelFactory(
    private val repository: ClienteRepository,
    private val clienteId: Long
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClienteEditViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ClienteEditViewModel(repository, clienteId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}