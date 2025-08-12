package eu.villacristina.lechugapro.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface IngresoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ingreso: Ingreso)

    @Update
    suspend fun update(ingreso: Ingreso)

    @Delete
    suspend fun delete(ingreso: Ingreso)

    @Query("SELECT * FROM ingresos WHERE id = :id")
    fun getIngresoById(id: Long): Flow<Ingreso?>

    /**
     * Obtiene todos los ingresos para un cliente específico, ordenados por fecha.
     * La consulta usa 'id_cliente' para la columna y 'fecha' para el orden.
     */
    @Query("SELECT * FROM ingresos WHERE id_cliente = :clienteId ORDER BY fecha DESC")
    fun getIngresosByClienteId(clienteId: Long): Flow<List<Ingreso>>

    @Query("SELECT * FROM ingresos ORDER BY fecha DESC")
    fun getAllIngresos(): Flow<List<Ingreso>>
}