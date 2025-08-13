package eu.villacristina.lechugapro.data

import kotlinx.coroutines.flow.Flow

/**
 * Repositorio para la entidad Ingreso.
 * Abstrae el acceso a los datos del DAO para los ViewModels.
 */
interface IngresoRepositoryContract {
    val todosLosIngresos: Flow<List<Ingreso>>
    fun getIngresosByClienteId(clienteId: Long): Flow<List<Ingreso>>
    fun getIngresoById(id: Long): Flow<Ingreso?>
    suspend fun insert(ingreso: Ingreso)
    suspend fun update(ingreso: Ingreso)
    suspend fun delete(ingreso: Ingreso)
}

class IngresoRepository(private val ingresoDao: IngresoDao) : IngresoRepositoryContract {

    // --- PROPIEDAD AÑADIDA AQUÍ ---
    // Expone el Flow para obtener TODOS los ingresos, ordenados por fecha.
    override val todosLosIngresos: Flow<List<Ingreso>> = ingresoDao.getAllIngresos()

    // Obtiene todos los ingresos de un cliente específico.
    override fun getIngresosByClienteId(clienteId: Long): Flow<List<Ingreso>> {
        return ingresoDao.getIngresosByClienteId(clienteId)
    }

    // Obtiene un ingreso por su ID.
    override fun getIngresoById(id: Long): Flow<Ingreso?> {
        return ingresoDao.getIngresoById(id)
    }

    // Inserta un nuevo ingreso.
    override suspend fun insert(ingreso: Ingreso) {
        ingresoDao.insert(ingreso)
    }

    // Actualiza un ingreso existente.
    override suspend fun update(ingreso: Ingreso) {
        ingresoDao.update(ingreso)
    }

    // Borra un ingreso.
    override suspend fun delete(ingreso: Ingreso) {
        ingresoDao.delete(ingreso)
    }
}
