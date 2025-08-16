package eu.villacristina.lechugapro.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\'\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\u000e\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\r\u00a8\u0006\u000f"}, d2 = {"Leu/villacristina/lechugapro/data/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "cicloProduccionDao", "Leu/villacristina/lechugapro/data/CicloProduccionDao;", "clienteDao", "Leu/villacristina/lechugapro/data/ClienteDao;", "gastoDao", "Leu/villacristina/lechugapro/data/GastoDao;", "ingresoDao", "Leu/villacristina/lechugapro/data/IngresoDao;", "seedGastosTiposIfEmpty", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
@androidx.room.Database(entities = {eu.villacristina.lechugapro.data.CicloProduccion.class, eu.villacristina.lechugapro.data.Cliente.class, eu.villacristina.lechugapro.data.Ingreso.class, eu.villacristina.lechugapro.data.Gasto.class}, version = 11, exportSchema = false)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile eu.villacristina.lechugapro.data.AppDatabase INSTANCE;
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
    
    @org.jetbrains.annotations.NotNull()
    public abstract eu.villacristina.lechugapro.data.GastoDao gastoDao();
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object seedGastosTiposIfEmpty(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Leu/villacristina/lechugapro/data/AppDatabase$Companion;", "", "()V", "INSTANCE", "Leu/villacristina/lechugapro/data/AppDatabase;", "getDatabase", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final eu.villacristina.lechugapro.data.AppDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}