package eu.villacristina.lechugapro.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clientes")
data class Cliente(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nombreCompleto: String,
    val telefono: String?,
    val email: String?,
    val direccion: String?,
    val notasCliente: String?
)
