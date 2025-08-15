package eu.villacristina.lechugapro.data

import androidx.room.*

@Dao
interface GastoDao {
    @Query("SELECT * FROM gastos WHERE id_ciclo = :cicloId")
    suspend fun getGastosPorCiclo(cicloId: Long): List<Gasto>

    @Query("SELECT * FROM gastos")
    suspend fun getTodosGastos(): List<Gasto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGasto(gasto: Gasto): Long

    @Update
    suspend fun updateGasto(gasto: Gasto)

    @Delete
    suspend fun deleteGasto(gasto: Gasto)
}
