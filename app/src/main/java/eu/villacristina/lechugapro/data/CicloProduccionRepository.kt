package eu.villacristina.lechugapro.data

import androidx.lifecycle.LiveData

class CicloProduccionRepository(private val cicloProduccionDao: CicloProduccionDao) {

    val ciclosActivos: LiveData<List<CicloProduccion>> = cicloProduccionDao.getAllActivos()
    val ciclosArchivados: LiveData<List<CicloProduccion>> = cicloProduccionDao.getArchivados()

    fun obtenerCicloPorId(id: Long): LiveData<CicloProduccion?> {
        return cicloProduccionDao.getById(id)
    }

    suspend fun insert(ciclo: CicloProduccion): Long {
        return cicloProduccionDao.insert(ciclo)
    }

    suspend fun update(ciclo: CicloProduccion) {
        cicloProduccionDao.update(ciclo)
    }

    suspend fun archivar(id: Long) {
        cicloProduccionDao.archivar(id)
    }

    suspend fun eliminar(id: Long) {
        cicloProduccionDao.deleteById(id)
    }

    suspend fun existeNumeroCiclo(numero: Int, excludeId: Long? = null): Boolean {
        return cicloProduccionDao.existsByNumeroCiclo(numero, excludeId)
    }
}