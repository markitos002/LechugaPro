package eu.villacristina.lechugapro.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

// Migraciones globales
// Migración: ingresos.fecha (TEXT con timestamp) -> INTEGER (epoch millis)
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
        db.execSQL(
            """
            INSERT INTO ingresos_new (id, id_cliente, fecha, concepto, importe, notas)
            SELECT id, id_cliente, CAST(fecha AS INTEGER), concepto, importe, notas FROM ingresos;
            """.trimIndent()
        )
        db.execSQL("DROP TABLE ingresos")
        db.execSQL("ALTER TABLE ingresos_new RENAME TO ingresos")
        db.execSQL("CREATE INDEX IF NOT EXISTS index_ingresos_id_cliente ON ingresos(id_cliente)")
    }
}
val MIGRATION_4_5 = object : Migration(4, 5) {
    override fun migrate(db: SupportSQLiteDatabase) {
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
            db.execSQL("DROP TABLE IF EXISTS ciclos_produccion_new")
        }
    }
}
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
val MIGRATION_6_7 = object : Migration(6, 7) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("CREATE INDEX IF NOT EXISTS index_ciclos_produccion_numeroCiclo ON ciclos_produccion(numeroCiclo)")
    }
}
val MIGRATION_7_8 = object : Migration(7, 8) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("DROP INDEX IF EXISTS index_ciclos_produccion_numeroCiclo")
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_ciclos_produccion_numeroCiclo ON ciclos_produccion(numeroCiclo)")
    }
}
val MIGRATION_8_9 = object : Migration(8, 9) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE ciclos_produccion ADD COLUMN fechaAntifungico INTEGER")
        db.execSQL("ALTER TABLE ciclos_produccion ADD COLUMN fechaK1 INTEGER")
        db.execSQL("ALTER TABLE ciclos_produccion ADD COLUMN fechaK2 INTEGER")
        db.execSQL("ALTER TABLE ciclos_produccion ADD COLUMN fechaK3 INTEGER")
    }
}
val MIGRATION_9_10 = object : Migration(9, 10) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE ingresos ADD COLUMN estado_pago TEXT NOT NULL DEFAULT 'Pagado'")
        db.execSQL("UPDATE ingresos SET estado_pago = 'En deuda' WHERE notas = 'En deuda'")
        db.execSQL("UPDATE ingresos SET estado_pago = 'Pagado' WHERE notas = 'Pagado'")
    }
}
val MIGRATION_10_11 = object : Migration(10, 11) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS gastos (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                id_ciclo INTEGER NOT NULL,
                tipo TEXT NOT NULL,
                importe REAL NOT NULL,
                fecha INTEGER NOT NULL
            )
            """.trimIndent()
        )
        db.execSQL("CREATE INDEX IF NOT EXISTS index_gastos_id_ciclo ON gastos(id_ciclo)")
    }
}

@Database(entities = [CicloProduccion::class, Cliente::class, Ingreso::class, Gasto::class], version = 11, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cicloProduccionDao(): CicloProduccionDao
    abstract fun clienteDao(): ClienteDao
    abstract fun ingresoDao(): IngresoDao
    abstract fun gastoDao(): GastoDao

