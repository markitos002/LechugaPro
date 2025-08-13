package eu.villacristina.lechugapro.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [CicloProduccion::class, Cliente::class, Ingreso::class], version = 4, exportSchema = false)
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
                // Añadimos migración 3->4 para cambiar tipo de columna fecha de TEXT a INTEGER
                .addMigrations(MIGRATION_3_4)
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }

        // Migración: ingresos.fecha (TEXT con timestamp) -> INTEGER (epoch millis)
        // Estrategia: crear tabla nueva, copiar datos casteando, reemplazar.
        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
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
                database.execSQL(
                    """
                    INSERT INTO ingresos_new (id, id_cliente, fecha, concepto, importe, notas)
                    SELECT id, id_cliente, CAST(fecha AS INTEGER), concepto, importe, notas FROM ingresos;
                    """.trimIndent()
                )
                database.execSQL("DROP TABLE ingresos")
                database.execSQL("ALTER TABLE ingresos_new RENAME TO ingresos")
                // Re-crear índices necesarios
                database.execSQL("CREATE INDEX IF NOT EXISTS index_ingresos_id_cliente ON ingresos(id_cliente)")
            }
        }
    }
}
