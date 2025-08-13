package eu.villacristina.lechugapro.ui.comercializacion.cliente

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eu.villacristina.lechugapro.data.Cliente
import eu.villacristina.lechugapro.data.ClienteRepositoryContract
import eu.villacristina.lechugapro.data.Ingreso
import eu.villacristina.lechugapro.data.IngresoRepositoryContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ClienteDetalleViewModel(
    private val clienteRepository: ClienteRepositoryContract,
    private val ingresoRepository: IngresoRepositoryContract,
    private val clienteId: Long
) : ViewModel() {

    private val _cliente = MutableStateFlow<Cliente?>(null)
    val cliente: StateFlow<Cliente?> = _cliente.asStateFlow()

    // StateFlow para la lista de ingresos
    private val _ingresos = MutableStateFlow<List<Ingreso>>(emptyList())
    val ingresos: StateFlow<List<Ingreso>> = _ingresos.asStateFlow()

    init {
        // Cargar datos del cliente
        viewModelScope.launch {
            clienteRepository.getClienteById(clienteId).collectLatest {
                _cliente.value = it
            }
        }
        // Cargar lista de ingresos para ese cliente
        viewModelScope.launch {
            // --- LLAMADA CORREGIDA AQUÍ ---
            ingresoRepository.getIngresosByClienteId(clienteId).collectLatest {
                _ingresos.value = it
            }
        }
    }

    fun deleteIngreso(ingreso: Ingreso) = viewModelScope.launch {
        ingresoRepository.delete(ingreso)
    }

    fun reInsertIngreso(ingreso: Ingreso) = viewModelScope.launch {
        ingresoRepository.insert(ingreso)
    }

    fun deleteCliente() = viewModelScope.launch {
        _cliente.value?.let { clienteRepository.delete(it) }
    }
}

// El Factory ahora necesita ambos repositorios
class ClienteDetalleViewModelFactory(
    private val clienteRepository: ClienteRepositoryContract,
    private val ingresoRepository: IngresoRepositoryContract,
    private val clienteId: Long
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClienteDetalleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ClienteDetalleViewModel(clienteRepository, ingresoRepository, clienteId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}