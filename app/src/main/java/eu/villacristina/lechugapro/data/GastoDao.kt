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

    @Query("SELECT COUNT(*) FROM gastos WHERE tipo = :tipo")
    suspend fun countByTipo(tipo: String): Int

    @Query("SELECT COUNT(*) FROM gastos WHERE id_ciclo = :cicloId AND tipo = :tipo")
    suspend fun countByTipoEnCiclo(cicloId: Long, tipo: String): Int

    @Query("SELECT COUNT(*) FROM gastos WHERE id_ciclo = 0 AND tipo = :tipo")
    suspend fun countByTipoGlobal(tipo: String): Int

    @Query("SELECT IFNULL(SUM(importe), 0) FROM gastos WHERE id_ciclo = :cicloId")
    suspend fun sumImportePorCiclo(cicloId: Long): Double

    @Query("SELECT IFNULL(SUM(importe), 0) FROM gastos WHERE id_ciclo = 0")
    suspend fun sumImporteGlobal(): Double
}
