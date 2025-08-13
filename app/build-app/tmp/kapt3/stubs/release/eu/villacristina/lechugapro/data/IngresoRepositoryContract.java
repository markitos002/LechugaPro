package eu.villacristina.lechugapro.data;

/**
 * Repositorio para la entidad Ingreso.
 * Abstrae el acceso a los datos del DAO para los ViewModels.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u000bJ\u0018\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00032\u0006\u0010\r\u001a\u00020\u000eH&J\u001c\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0010\u001a\u00020\u000eH&J\u0016\u0010\u0011\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\u0012\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u000bR\u001e\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Leu/villacristina/lechugapro/data/IngresoRepositoryContract;", "", "todosLosIngresos", "Lkotlinx/coroutines/flow/Flow;", "", "Leu/villacristina/lechugapro/data/Ingreso;", "getTodosLosIngresos", "()Lkotlinx/coroutines/flow/Flow;", "delete", "", "ingreso", "(Leu/villacristina/lechugapro/data/Ingreso;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getIngresoById", "id", "", "getIngresosByClienteId", "clienteId", "insert", "update", "app_release"})
public abstract interface IngresoRepositoryContract {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<eu.villacristina.lechugapro.data.Ingreso>> getTodosLosIngresos();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<eu.villacristina.lechugapro.data.Ingreso>> getIngresosByClienteId(long clienteId);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<eu.villacristina.lechugapro.data.Ingreso> getIngresoById(long id);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.Ingreso ingreso, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.Ingreso ingreso, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.Ingreso ingreso, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}