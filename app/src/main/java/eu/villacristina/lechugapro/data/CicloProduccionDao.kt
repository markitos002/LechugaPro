package eu.villacristina.lechugapro.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CicloProduccionDao {
    @Query("SELECT * FROM ciclos_produccion")
    fun getAll(): LiveData<List<CicloProduccion>>

    @Query("SELECT * FROM ciclos_produccion WHERE id = :id")
    fun getById(id: Long): LiveData<CicloProduccion?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ciclo: CicloProduccion): Long

    @Update
    suspend fun update(ciclo: CicloProduccion)
}