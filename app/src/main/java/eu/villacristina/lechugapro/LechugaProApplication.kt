package eu.villacristina.lechugapro

import android.app.Application
import eu.villacristina.lechugapro.data.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LechugaProApplication : Application() {

    // Using by lazy so the database is only created when it's needed
    // rather than when the application starts.
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        // Semilla inicial de gastos si la tabla está vacía
        CoroutineScope(Dispatchers.IO).launch {
            try { database.seedGastosTiposIfEmpty() } catch (_: Exception) { }
        }
    }
}
