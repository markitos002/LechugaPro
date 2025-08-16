package eu.villacristina.lechugapro.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH\'J\u0018\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\b2\u0006\u0010\u000b\u001a\u00020\fH\'J\u001c\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\b2\u0006\u0010\u000e\u001a\u00020\fH\'J\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\tH\u00a7@\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0016"}, d2 = {"Leu/villacristina/lechugapro/data/IngresoDao;", "", "delete", "", "ingreso", "Leu/villacristina/lechugapro/data/Ingreso;", "(Leu/villacristina/lechugapro/data/Ingreso;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllIngresos", "Lkotlinx/coroutines/flow/Flow;", "", "getIngresoById", "id", "", "getIngresosByClienteId", "clienteId", "getTotalesPorCliente", "Leu/villacristina/lechugapro/data/TotalPorCliente;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "sumImporteTotal", "", "update", "app_debug"})
@androidx.room.Dao()
public abstract interface IngresoDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.Ingreso ingreso, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.Ingreso ingreso, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.Ingreso ingreso, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM ingresos WHERE id = :id")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<eu.villacristina.lechugapro.data.Ingreso> getIngresoById(long id);
    
    /**
     * Obtiene todos los ingresos para un cliente específico, ordenados por fecha.
     * La consulta usa 'id_cliente' para la columna y 'fecha' para el orden.
     */
    @androidx.room.Query(value = "SELECT * FROM ingresos WHERE id_cliente = :clienteId ORDER BY fecha DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<eu.villacristina.lechugapro.data.Ingreso>> getIngresosByClienteId(long clienteId);
    
    @androidx.room.Query(value = "SELECT * FROM ingresos ORDER BY fecha DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<eu.villacristina.lechugapro.data.Ingreso>> getAllIngresos();
    
    @androidx.room.Query(value = "SELECT IFNULL(SUM(importe), 0) FROM ingresos")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sumImporteTotal(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Query(value = "SELECT c.id AS idCliente, c.nombreCompleto AS nombre, IFNULL(SUM(i.importe), 0) AS total FROM clientes c LEFT JOIN ingresos i ON i.id_cliente = c.id GROUP BY c.id, c.nombreCompleto ORDER BY total DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTotalesPorCliente(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<eu.villacristina.lechugapro.data.TotalPorCliente>> $completion);
}