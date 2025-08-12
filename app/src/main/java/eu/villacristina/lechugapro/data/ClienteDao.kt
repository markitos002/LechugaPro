package eu.villacristina.lechugapro.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ClienteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cliente: Cliente)

    @Update
    suspend fun update(cliente: Cliente)

    @Delete
    suspend fun delete(cliente: Cliente)

    @Query("SELECT * FROM clientes WHERE id = :id")
    fun getClienteById(id: Long): Flow<Cliente?>

    @Query("SELECT * FROM clientes ORDER BY nombreCompleto ASC")
    fun getAllClientes(): Flow<List<Cliente>>
}
