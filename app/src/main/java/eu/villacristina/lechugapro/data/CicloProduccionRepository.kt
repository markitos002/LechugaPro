package eu.villacristina.lechugapro.data

import androidx.lifecycle.LiveData

class CicloProduccionRepository(private val cicloProduccionDao: CicloProduccionDao) {

    val todosLosCiclos: LiveData<List<CicloProduccion>> = cicloProduccionDao.getAll()

    fun obtenerCicloPorId(id: Long): LiveData<CicloProduccion?> {
        return cicloProduccionDao.getById(id)
    }

    suspend fun insert(ciclo: CicloProduccion): Long {
        return cicloProduccionDao.insert(ciclo)
    }

    suspend fun update(ciclo: CicloProduccion) {
        cicloProduccionDao.update(ciclo)
    }

    suspend fun existeNumeroCiclo(numero: Int, excludeId: Long? = null): Boolean {
        return cicloProduccionDao.existsByNumeroCiclo(numero, excludeId)
    }
}