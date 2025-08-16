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

    @Query("SELECT IFNULL(SUM(importe), 0) FROM ingresos")
    suspend fun sumImporteTotal(): Double

    // Totales por cliente para resumen
    @Query("SELECT c.id AS idCliente, c.nombreCompleto AS nombre, IFNULL(SUM(i.importe), 0) AS total " +
        "FROM clientes c LEFT JOIN ingresos i ON i.id_cliente = c.id " +
        "GROUP BY c.id, c.nombreCompleto ORDER BY total DESC")
    suspend fun getTotalesPorCliente(): List<TotalPorCliente>
}