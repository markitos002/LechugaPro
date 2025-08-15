package eu.villacristina.lechugapro.ui.comercializacion.cliente;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\f\u00a8\u0006\u0014"}, d2 = {"Leu/villacristina/lechugapro/ui/comercializacion/cliente/ClienteListaViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Leu/villacristina/lechugapro/data/ClienteRepository;", "ingresoRepository", "Leu/villacristina/lechugapro/data/IngresoRepository;", "(Leu/villacristina/lechugapro/data/ClienteRepository;Leu/villacristina/lechugapro/data/IngresoRepository;)V", "clientesConDeuda", "Lkotlinx/coroutines/flow/StateFlow;", "", "", "getClientesConDeuda", "()Lkotlinx/coroutines/flow/StateFlow;", "todosLosClientes", "", "Leu/villacristina/lechugapro/data/Cliente;", "getTodosLosClientes", "totalIngresos", "", "getTotalIngresos", "app_debug"})
public final class ClienteListaViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<eu.villacristina.lechugapro.data.Cliente>> todosLosClientes = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Double> totalIngresos = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.Set<java.lang.Long>> clientesConDeuda = null;
    
    public ClienteListaViewModel(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.ClienteRepository repository, @org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.IngresoRepository ingresoRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<eu.villacristina.lechugapro.data.Cliente>> getTodosLosClientes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Double> getTotalIngresos() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.Set<java.lang.Long>> getClientesConDeuda() {
        return null;
    }
}