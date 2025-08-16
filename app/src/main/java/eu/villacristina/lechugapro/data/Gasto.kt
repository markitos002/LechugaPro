package eu.villacristina.lechugapro.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Index

@Entity(
    tableName = "gastos",
    indices = [Index(value = ["id_ciclo"], name = "index_gastos_id_ciclo")]
)
data class Gasto(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "id_ciclo") val idCiclo: Long,
    @ColumnInfo(name = "tipo") val tipo: String, // Ej: "preparacion", "potasio", etc.
    @ColumnInfo(name = "importe") val importe: Double,
    @ColumnInfo(name = "fecha") val fecha: Long // epoch millis
)
