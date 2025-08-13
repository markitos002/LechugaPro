package eu.villacristina.lechugapro.ui.comercializacion.cliente;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\tJ\u000e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\tR\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Leu/villacristina/lechugapro/ui/comercializacion/cliente/ClienteEditViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Leu/villacristina/lechugapro/data/ClienteRepository;", "clienteId", "", "(Leu/villacristina/lechugapro/data/ClienteRepository;J)V", "_cliente", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Leu/villacristina/lechugapro/data/Cliente;", "cliente", "Lkotlinx/coroutines/flow/StateFlow;", "getCliente", "()Lkotlinx/coroutines/flow/StateFlow;", "insert", "Lkotlinx/coroutines/Job;", "update", "app_release"})
public final class ClienteEditViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final eu.villacristina.lechugapro.data.ClienteRepository repository = null;
    private final long clienteId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<eu.villacristina.lechugapro.data.Cliente> _cliente = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<eu.villacristina.lechugapro.data.Cliente> cliente = null;
    
    public ClienteEditViewModel(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.ClienteRepository repository, long clienteId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<eu.villacristina.lechugapro.data.Cliente> getCliente() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job insert(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.Cliente cliente) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job update(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.Cliente cliente) {
        return null;
    }
}