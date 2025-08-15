package eu.villacristina.lechugapro.ui.comercializacion.cliente;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u000eJ\u000e\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u000eR\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012\u00a8\u0006\u001a"}, d2 = {"Leu/villacristina/lechugapro/ui/comercializacion/cliente/ClienteDetalleViewModel;", "Landroidx/lifecycle/ViewModel;", "clienteRepository", "Leu/villacristina/lechugapro/data/ClienteRepositoryContract;", "ingresoRepository", "Leu/villacristina/lechugapro/data/IngresoRepositoryContract;", "clienteId", "", "(Leu/villacristina/lechugapro/data/ClienteRepositoryContract;Leu/villacristina/lechugapro/data/IngresoRepositoryContract;J)V", "_cliente", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Leu/villacristina/lechugapro/data/Cliente;", "_ingresos", "", "Leu/villacristina/lechugapro/data/Ingreso;", "cliente", "Lkotlinx/coroutines/flow/StateFlow;", "getCliente", "()Lkotlinx/coroutines/flow/StateFlow;", "ingresos", "getIngresos", "deleteCliente", "Lkotlinx/coroutines/Job;", "deleteIngreso", "ingreso", "reInsertIngreso", "app_debug"})
public final class ClienteDetalleViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final eu.villacristina.lechugapro.data.ClienteRepositoryContract clienteRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final eu.villacristina.lechugapro.data.IngresoRepositoryContract ingresoRepository = null;
    private final long clienteId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<eu.villacristina.lechugapro.data.Cliente> _cliente = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<eu.villacristina.lechugapro.data.Cliente> cliente = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<eu.villacristina.lechugapro.data.Ingreso>> _ingresos = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<eu.villacristina.lechugapro.data.Ingreso>> ingresos = null;
    
    public ClienteDetalleViewModel(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.ClienteRepositoryContract clienteRepository, @org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.IngresoRepositoryContract ingresoRepository, long clienteId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<eu.villacristina.lechugapro.data.Cliente> getCliente() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<eu.villacristina.lechugapro.data.Ingreso>> getIngresos() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job deleteIngreso(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.Ingreso ingreso) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job reInsertIngreso(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.Ingreso ingreso) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job deleteCliente() {
        return null;
    }
}