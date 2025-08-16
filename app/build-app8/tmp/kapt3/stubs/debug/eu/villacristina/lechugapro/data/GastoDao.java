package eu.villacristina.lechugapro.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\rH\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\u0014\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\u0015"}, d2 = {"Leu/villacristina/lechugapro/data/GastoDao;", "", "countByTipo", "", "tipo", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteGasto", "", "gasto", "Leu/villacristina/lechugapro/data/Gasto;", "(Leu/villacristina/lechugapro/data/Gasto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getGastosPorCiclo", "", "cicloId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTodosGastos", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertGasto", "updateGasto", "app_debug"})
@androidx.room.Dao()
public abstract interface GastoDao {
    
    @androidx.room.Query(value = "SELECT * FROM gastos WHERE id_ciclo = :cicloId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getGastosPorCiclo(long cicloId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<eu.villacristina.lechugapro.data.Gasto>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM gastos")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTodosGastos(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<eu.villacristina.lechugapro.data.Gasto>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertGasto(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.Gasto gasto, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateGasto(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.Gasto gasto, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteGasto(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.Gasto gasto, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM gastos WHERE tipo = :tipo")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object countByTipo(@org.jetbrains.annotations.NotNull()
    java.lang.String tipo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}