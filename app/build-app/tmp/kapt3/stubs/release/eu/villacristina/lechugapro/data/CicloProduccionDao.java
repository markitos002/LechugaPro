package eu.villacristina.lechugapro.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u00a7@\u00a2\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fH\'J\u0014\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fH\'J\u0018\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u000f2\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0016\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u001e\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u001aH\u00a7@\u00a2\u0006\u0002\u0010\u001b\u00a8\u0006\u001c"}, d2 = {"Leu/villacristina/lechugapro/data/CicloProduccionDao;", "", "archivar", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteById", "existsByNumeroCiclo", "", "numero", "", "excludeId", "(ILjava/lang/Long;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllActivos", "Landroidx/lifecycle/LiveData;", "", "Leu/villacristina/lechugapro/data/CicloProduccion;", "getArchivados", "getById", "insert", "ciclo", "(Leu/villacristina/lechugapro/data/CicloProduccion;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "updateEstado", "estado", "", "(JLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
@androidx.room.Dao()
public abstract interface CicloProduccionDao {
    
    @androidx.room.Query(value = "SELECT * FROM ciclos_produccion WHERE estado != \'Archivado\' OR estado IS NULL")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<eu.villacristina.lechugapro.data.CicloProduccion>> getAllActivos();
    
    @androidx.room.Query(value = "SELECT * FROM ciclos_produccion WHERE estado = \'Archivado\'")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<eu.villacristina.lechugapro.data.CicloProduccion>> getArchivados();
    
    @androidx.room.Query(value = "SELECT * FROM ciclos_produccion WHERE id = :id")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<eu.villacristina.lechugapro.data.CicloProduccion> getById(long id);
    
    @androidx.room.Query(value = "SELECT EXISTS(SELECT 1 FROM ciclos_produccion WHERE numeroCiclo = :numero AND (:excludeId IS NULL OR id != :excludeId))")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object existsByNumeroCiclo(int numero, @org.jetbrains.annotations.Nullable()
    java.lang.Long excludeId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.CicloProduccion ciclo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.CicloProduccion ciclo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE ciclos_produccion SET estado = \'Archivado\' WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object archivar(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE ciclos_produccion SET estado = :estado WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateEstado(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String estado, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM ciclos_produccion WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}