package eu.villacristina.lechugapro.data

import kotlinx.coroutines.flow.Flow

class ClienteRepository(private val clienteDao: ClienteDao) {

    val todosLosClientes: Flow<List<Cliente>> = clienteDao.getAllClientes()

    fun getClienteById(id: Long): Flow<Cliente?> {
        return clienteDao.getClienteById(id)
    }

    suspend fun insert(cliente: Cliente) {
        clienteDao.insert(cliente)
    }

    suspend fun update(cliente: Cliente) {
        clienteDao.update(cliente)
    }

    suspend fun delete(cliente: Cliente) {
        clienteDao.delete(cliente)
    }
}
