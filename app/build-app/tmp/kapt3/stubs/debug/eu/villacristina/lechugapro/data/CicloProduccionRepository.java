package eu.villacristina.lechugapro.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0015J\"\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0010H\u0086@\u00a2\u0006\u0002\u0010\u001cJ\u0016\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u001fJ\u0016\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010!\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u001fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n\u00a8\u0006\""}, d2 = {"Leu/villacristina/lechugapro/data/CicloProduccionRepository;", "", "cicloProduccionDao", "Leu/villacristina/lechugapro/data/CicloProduccionDao;", "(Leu/villacristina/lechugapro/data/CicloProduccionDao;)V", "ciclosActivos", "Landroidx/lifecycle/LiveData;", "", "Leu/villacristina/lechugapro/data/CicloProduccion;", "getCiclosActivos", "()Landroidx/lifecycle/LiveData;", "ciclosArchivados", "getCiclosArchivados", "actualizarEstado", "", "id", "", "estado", "", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "archivar", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "eliminar", "existeNumeroCiclo", "", "numero", "", "excludeId", "(ILjava/lang/Long;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "ciclo", "(Leu/villacristina/lechugapro/data/CicloProduccion;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "obtenerCicloPorId", "update", "app_debug"})
public final class CicloProduccionRepository {
    @org.jetbrains.annotations.NotNull()
    private final eu.villacristina.lechugapro.data.CicloProduccionDao cicloProduccionDao = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<eu.villacristina.lechugapro.data.CicloProduccion>> ciclosActivos = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<eu.villacristina.lechugapro.data.CicloProduccion>> ciclosArchivados = null;
    
    public CicloProduccionRepository(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.CicloProduccionDao cicloProduccionDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<eu.villacristina.lechugapro.data.CicloProduccion>> getCiclosActivos() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<eu.villacristina.lechugapro.data.CicloProduccion>> getCiclosArchivados() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<eu.villacristina.lechugapro.data.CicloProduccion> obtenerCicloPorId(long id) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.CicloProduccion ciclo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object update(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.CicloProduccion ciclo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object archivar(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object eliminar(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object actualizarEstado(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String estado, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object existeNumeroCiclo(int numero, @org.jetbrains.annotations.Nullable()
    java.lang.Long excludeId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
}