package eu.villacristina.lechugapro.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nH\'J\u0018\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\n2\u0006\u0010\u000e\u001a\u00020\u0007H\'J\u0016\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\u0011\u00a8\u0006\u0014"}, d2 = {"Leu/villacristina/lechugapro/data/CicloProduccionDao;", "", "existsByNumeroCiclo", "", "numero", "", "excludeId", "", "(ILjava/lang/Long;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "Landroidx/lifecycle/LiveData;", "", "Leu/villacristina/lechugapro/data/CicloProduccion;", "getById", "id", "insert", "ciclo", "(Leu/villacristina/lechugapro/data/CicloProduccion;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "", "app_release"})
@androidx.room.Dao()
public abstract interface CicloProduccionDao {
    
    @androidx.room.Query(value = "SELECT * FROM ciclos_produccion")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<eu.villacristina.lechugapro.data.CicloProduccion>> getAll();
    
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
}