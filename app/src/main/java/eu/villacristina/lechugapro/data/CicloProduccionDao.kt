package eu.villacristina.lechugapro.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CicloProduccionDao {
    @Query("SELECT * FROM ciclos_produccion WHERE estado != 'Archivado' OR estado IS NULL")
    fun getAllActivos(): LiveData<List<CicloProduccion>>

    @Query("SELECT * FROM ciclos_produccion WHERE estado = 'Archivado'")
    fun getArchivados(): LiveData<List<CicloProduccion>>

    @Query("SELECT * FROM ciclos_produccion WHERE id = :id")
    fun getById(id: Long): LiveData<CicloProduccion?>

    @Query("SELECT EXISTS(SELECT 1 FROM ciclos_produccion WHERE numeroCiclo = :numero AND (:excludeId IS NULL OR id != :excludeId))")
    suspend fun existsByNumeroCiclo(numero: Int, excludeId: Long?): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ciclo: CicloProduccion): Long

    @Update
    suspend fun update(ciclo: CicloProduccion)

    @Query("UPDATE ciclos_produccion SET estado = 'Archivado' WHERE id = :id")
    suspend fun archivar(id: Long)

    @Query("UPDATE ciclos_produccion SET estado = :estado WHERE id = :id")
    suspend fun updateEstado(id: Long, estado: String)

    @Query("DELETE FROM ciclos_produccion WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("SELECT * FROM ciclos_produccion WHERE estado != 'Archivado' OR estado IS NULL ORDER BY id DESC LIMIT 1")
    suspend fun getUltimoActivo(): CicloProduccion?
}