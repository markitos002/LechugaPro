package eu.villacristina.lechugapro.data

import kotlinx.coroutines.flow.Flow

interface ClienteRepositoryContract {
    val todosLosClientes: Flow<List<Cliente>>
    fun getClienteById(id: Long): Flow<Cliente?>
    suspend fun insert(cliente: Cliente)
    suspend fun update(cliente: Cliente)
    suspend fun delete(cliente: Cliente)
}

class ClienteRepository(private val clienteDao: ClienteDao) : ClienteRepositoryContract {

    override val todosLosClientes: Flow<List<Cliente>> = clienteDao.getAllClientes()

    override fun getClienteById(id: Long): Flow<Cliente?> {
        return clienteDao.getClienteById(id)
    }

    override suspend fun insert(cliente: Cliente) {
        clienteDao.insert(cliente)
    }

    override suspend fun update(cliente: Cliente) {
        clienteDao.update(cliente)
    }

    override suspend fun delete(cliente: Cliente) {
        clienteDao.delete(cliente)
    }
}
