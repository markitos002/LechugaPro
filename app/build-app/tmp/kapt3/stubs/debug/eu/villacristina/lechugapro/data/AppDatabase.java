package eu.villacristina.lechugapro.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\n"}, d2 = {"Leu/villacristina/lechugapro/data/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "cicloProduccionDao", "Leu/villacristina/lechugapro/data/CicloProduccionDao;", "clienteDao", "Leu/villacristina/lechugapro/data/ClienteDao;", "ingresoDao", "Leu/villacristina/lechugapro/data/IngresoDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {eu.villacristina.lechugapro.data.CicloProduccion.class, eu.villacristina.lechugapro.data.Cliente.class, eu.villacristina.lechugapro.data.Ingreso.class}, version = 9, exportSchema = false)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile eu.villacristina.lechugapro.data.AppDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.room.migration.Migration MIGRATION_3_4 = null;
    @org.jetbrains.annotations.NotNull()
    public static final eu.villacristina.lechugapro.data.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract eu.villacristina.lechugapro.data.CicloProduccionDao cicloProduccionDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract eu.villacristina.lechugapro.data.ClienteDao clienteDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract eu.villacristina.lechugapro.data.IngresoDao ingresoDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\f"}, d2 = {"Leu/villacristina/lechugapro/data/AppDatabase$Companion;", "", "()V", "INSTANCE", "Leu/villacristina/lechugapro/data/AppDatabase;", "MIGRATION_3_4", "Landroidx/room/migration/Migration;", "getMIGRATION_3_4", "()Landroidx/room/migration/Migration;", "getDatabase", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final eu.villacristina.lechugapro.data.AppDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.room.migration.Migration getMIGRATION_3_4() {
            return null;
        }
    }
}