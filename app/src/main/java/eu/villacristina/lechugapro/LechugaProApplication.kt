package eu.villacristina.lechugapro

import android.app.Application
import eu.villacristina.lechugapro.data.AppDatabase

class LechugaProApplication : Application() {

    // Using by lazy so the database is only created when it's needed
    // rather than when the application starts.
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}
