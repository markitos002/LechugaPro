package eu.villacristina.lechugapro.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "ciclos_produccion",
    indices = [Index(value = ["numeroCiclo"], unique = true)]
)
data class CicloProduccion(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    var numeroCiclo: Int? = null,

    // --- Campos añadidos/actualizados ---
    var variedad: String? = null,
    var numeroPlantas: Int = 0,
    // ------------------------------------

    var fechaInicioPreparacionTierra: Long? = null,
    var fechaFinPreparacionTierra: Long? = null,
    var fechaAbono: Long? = null,
    var fechaSiembra: Long? = null,
    var fechaSuplementoMinerales: Long? = null,
    var fechaEstimadaCosecha: Long? = null,
    var fechaRealCosecha: Long? = null,

    // Nuevas fechas persistidas derivadas desde siembra
    var fechaAntifungico: Long? = null,
    var fechaK1: Long? = null,
    var fechaK2: Long? = null,
    var fechaK3: Long? = null,

    var notas: String? = null,
    var estado: String = "Planificado"
)
