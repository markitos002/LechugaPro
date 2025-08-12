package eu.villacristina.lechugapro.data

import kotlinx.coroutines.flow.Flow

/**
 * Repositorio para la entidad Ingreso.
 * Abstrae el acceso a los datos del DAO para los ViewModels.
 */
class IngresoRepository(private val ingresoDao: IngresoDao) {

    // --- PROPIEDAD AÑADIDA AQUÍ ---
    // Expone el Flow para obtener TODOS los ingresos, ordenados por fecha.
    val todosLosIngresos: Flow<List<Ingreso>> = ingresoDao.getAllIngresos()

    // Obtiene todos los ingresos de un cliente específico.
    fun getIngresosByClienteId(clienteId: Long): Flow<List<Ingreso>> {
        return ingresoDao.getIngresosByClienteId(clienteId)
    }

    // Obtiene un ingreso por su ID.
    fun getIngresoById(id: Long): Flow<Ingreso?> {
        return ingresoDao.getIngresoById(id)
    }

    // Inserta un nuevo ingreso.
    suspend fun insert(ingreso: Ingreso) {
        ingresoDao.insert(ingreso)
    }

    // Actualiza un ingreso existente.
    suspend fun update(ingreso: Ingreso) {
        ingresoDao.update(ingreso)
    }

    // Borra un ingreso.
    suspend fun delete(ingreso: Ingreso) {
        ingresoDao.delete(ingreso)
    }
}
