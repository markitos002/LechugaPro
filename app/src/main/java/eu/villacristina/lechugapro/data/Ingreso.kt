package eu.villacristina.lechugapro.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Entidad que representa un único ingreso o venta.
 * La columna en la base de datos que relaciona con Cliente se llama "id_cliente".
 */
@Entity(
    tableName = "ingresos",
    foreignKeys = [
        ForeignKey(
            entity = Cliente::class,
            parentColumns = ["id"],
            childColumns = ["id_cliente"], // Columna en la tabla 'ingresos'
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Ingreso(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "id_cliente", index = true)
    val idCliente: Long, // ID del cliente al que pertenece el ingreso

    // Almacenar fecha como epoch millis (Long) para facilitar orden y cálculos
    val fecha: Long,
    val concepto: String,
    val importe: Double,
    @ColumnInfo(name = "estado_pago")
    val estadoPago: String = "Pagado",
    val notas: String? = null
)