package eu.villacristina.lechugapro.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CicloProduccion::class, Cliente::class, Ingreso::class], version = 3, exportSchema = false)
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
                // OJO: La migración destructiva borrará los datos si cambias el esquema.
                // Para una app en producción, deberías implementar una migración real.
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
