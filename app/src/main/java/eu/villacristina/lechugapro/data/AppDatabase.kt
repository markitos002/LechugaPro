package eu.villacristina.lechugapro.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [CicloProduccion::class, Cliente::class, Ingreso::class], version = 10, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cicloProduccionDao(): CicloProduccionDao
    abstract fun clienteDao(): ClienteDao
    abstract fun ingresoDao(): IngresoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "lechuga_pro_database"
                )
                // Migraciones
                .addMigrations(MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6, MIGRATION_6_7, MIGRATION_7_8, MIGRATION_8_9, MIGRATION_9_10)
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }

        // Migración: ingresos.fecha (TEXT con timestamp) -> INTEGER (epoch millis)
        // Estrategia: crear tabla nueva, copiar datos casteando, reemplazar.
        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS ingresos_new (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        id_cliente INTEGER NOT NULL,
                        fecha INTEGER NOT NULL,
                        concepto TEXT NOT NULL,
                        importe REAL NOT NULL,
                        notas TEXT,
                        FOREIGN KEY(id_cliente) REFERENCES clientes(id) ON DELETE CASCADE
                    )
                    """.trimIndent()
                )
                // Copiar datos (CAST de texto a INTEGER); si algún valor no es convertible caerá en 0
                db.execSQL(
                    """
                    INSERT INTO ingresos_new (id, id_cliente, fecha, concepto, importe, notas)
                    SELECT id, id_cliente, CAST(fecha AS INTEGER), concepto, importe, notas FROM ingresos;
                    """.trimIndent()
                )
                db.execSQL("DROP TABLE ingresos")
                db.execSQL("ALTER TABLE ingresos_new RENAME TO ingresos")
                // Re-crear índices necesarios
                db.execSQL("CREATE INDEX IF NOT EXISTS index_ingresos_id_cliente ON ingresos(id_cliente)")
            }
        }
    }
}

// Migración 4->5: Renombrar columna ciclos_produccion.nombreCiclo -> numeroCiclo
val MIGRATION_4_5 = object : Migration(4, 5) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // Crear nueva tabla con la columna ya renombrada
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS ciclos_produccion_new (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                numeroCiclo TEXT,
                variedad TEXT,
                numeroPlantas INTEGER NOT NULL,
                fechaInicioPreparacionTierra INTEGER,
                fechaFinPreparacionTierra INTEGER,
                fechaAbono INTEGER,
                fechaSiembra INTEGER,
                fechaSuplementoMinerales INTEGER,
                fechaEstimadaCosecha INTEGER,
                fechaRealCosecha INTEGER,
                notas TEXT,
                estado TEXT NOT NULL
            )
            """.trimIndent()
        )
        // Copiar datos desde la tabla antigua mapeando nombreCiclo -> numeroCiclo
        // Si nombreCiclo no existe (p.ej. DB fresca), este INSERT fallaría; por eso protegemos con verificación básica
        try {
            db.execSQL(
                """
                INSERT INTO ciclos_produccion_new (
                    id, numeroCiclo, variedad, numeroPlantas,
                    fechaInicioPreparacionTierra, fechaFinPreparacionTierra, fechaAbono,
                    fechaSiembra, fechaSuplementoMinerales, fechaEstimadaCosecha,
                    fechaRealCosecha, notas, estado
                )
                SELECT id, nombreCiclo, variedad, numeroPlantas,
                       fechaInicioPreparacionTierra, fechaFinPreparacionTierra, fechaAbono,
                       fechaSiembra, fechaSuplementoMinerales, fechaEstimadaCosecha,
                       fechaRealCosecha, notas, estado
                FROM ciclos_produccion;
                """.trimIndent()
            )
            db.execSQL("DROP TABLE ciclos_produccion")
            db.execSQL("ALTER TABLE ciclos_produccion_new RENAME TO ciclos_produccion")
        } catch (_: Exception) {
            // Si la tabla ya tiene numeroCiclo (por instalaciones nuevas), solo aseguramos el esquema
            db.execSQL("DROP TABLE IF EXISTS ciclos_produccion_new")
        }
    }
}

// Migración 5->6: Cambiar tipo de numeroCiclo de TEXT a INTEGER (nullable)
val MIGRATION_5_6 = object : Migration(5, 6) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS ciclos_produccion_tmp (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                numeroCiclo INTEGER,
                variedad TEXT,
                numeroPlantas INTEGER NOT NULL,
                fechaInicioPreparacionTierra INTEGER,
                fechaFinPreparacionTierra INTEGER,
                fechaAbono INTEGER,
                fechaSiembra INTEGER,
                fechaSuplementoMinerales INTEGER,
                fechaEstimadaCosecha INTEGER,
                fechaRealCosecha INTEGER,
                notas TEXT,
                estado TEXT NOT NULL
            )
            """.trimIndent()
        )
        // Copiar datos: convertir numeroCiclo de TEXT a INTEGER si es posible; si no, dejar NULL
        db.execSQL(
            """
            INSERT INTO ciclos_produccion_tmp (
                id, numeroCiclo, variedad, numeroPlantas,
                fechaInicioPreparacionTierra, fechaFinPreparacionTierra, fechaAbono,
                fechaSiembra, fechaSuplementoMinerales, fechaEstimadaCosecha,
                fechaRealCosecha, notas, estado
            )
            SELECT id,
                   CAST(NULLIF(TRIM(numeroCiclo), '') AS INTEGER),
                   variedad, numeroPlantas,
                   fechaInicioPreparacionTierra, fechaFinPreparacionTierra, fechaAbono,
                   fechaSiembra, fechaSuplementoMinerales, fechaEstimadaCosecha,
                   fechaRealCosecha, notas, estado
            FROM ciclos_produccion;
            """.trimIndent()
        )
        db.execSQL("DROP TABLE ciclos_produccion")
        db.execSQL("ALTER TABLE ciclos_produccion_tmp RENAME TO ciclos_produccion")
    }
}

// Migración 6->7: Crear índice sobre numeroCiclo
val MIGRATION_6_7 = object : Migration(6, 7) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("CREATE INDEX IF NOT EXISTS index_ciclos_produccion_numeroCiclo ON ciclos_produccion(numeroCiclo)")
    }
}

// Migración 7->8: Crear índice único sobre numeroCiclo
val MIGRATION_7_8 = object : Migration(7, 8) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // Eliminar índice previo si existe y luego crear uno único
        db.execSQL("DROP INDEX IF EXISTS index_ciclos_produccion_numeroCiclo")
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_ciclos_produccion_numeroCiclo ON ciclos_produccion(numeroCiclo)")
    }
}

// Migración 8->9: Agregar columnas persistidas para antifúngico y K1/K2/K3
val MIGRATION_8_9 = object : Migration(8, 9) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE ciclos_produccion ADD COLUMN fechaAntifungico INTEGER")
        db.execSQL("ALTER TABLE ciclos_produccion ADD COLUMN fechaK1 INTEGER")
        db.execSQL("ALTER TABLE ciclos_produccion ADD COLUMN fechaK2 INTEGER")
        db.execSQL("ALTER TABLE ciclos_produccion ADD COLUMN fechaK3 INTEGER")
    }
}

// Migración 9->10: Agregar columna estado_pago a ingresos y rellenar desde notas si coincide
val MIGRATION_9_10 = object : Migration(9, 10) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // Añadir columna con valor por defecto 'Pagado'
        db.execSQL("ALTER TABLE ingresos ADD COLUMN estado_pago TEXT NOT NULL DEFAULT 'Pagado'")
        // Si la columna notas contiene exactamente 'En deuda' o 'Pagado', usarla para estado_pago
        // Cualquier otro valor en notas se conserva como notas
        db.execSQL("UPDATE ingresos SET estado_pago = 'En deuda' WHERE notas = 'En deuda'")
        db.execSQL("UPDATE ingresos SET estado_pago = 'Pagado' WHERE notas = 'Pagado'")
    }
}
