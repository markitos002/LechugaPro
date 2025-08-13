package eu.villacristina.lechugapro.ui.comercializacion.ingreso;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007J&\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0015J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\nH\u0002J\u0010\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\nH\u0002R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Leu/villacristina/lechugapro/ui/comercializacion/ingreso/IngresoEditViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Leu/villacristina/lechugapro/data/IngresoRepositoryContract;", "clienteId", "", "ingresoId", "(Leu/villacristina/lechugapro/data/IngresoRepositoryContract;JJ)V", "_ingreso", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Leu/villacristina/lechugapro/data/Ingreso;", "getClienteId", "()J", "ingreso", "Lkotlinx/coroutines/flow/StateFlow;", "getIngreso", "()Lkotlinx/coroutines/flow/StateFlow;", "guardarIngreso", "", "fechaMillis", "concepto", "", "importe", "", "notas", "insert", "Lkotlinx/coroutines/Job;", "update", "app_release"})
public final class IngresoEditViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final eu.villacristina.lechugapro.data.IngresoRepositoryContract repository = null;
    private final long clienteId = 0L;
    private final long ingresoId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<eu.villacristina.lechugapro.data.Ingreso> _ingreso = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<eu.villacristina.lechugapro.data.Ingreso> ingreso = null;
    
    public IngresoEditViewModel(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.IngresoRepositoryContract repository, long clienteId, long ingresoId) {
        super();
    }
    
    public final long getClienteId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<eu.villacristina.lechugapro.data.Ingreso> getIngreso() {
        return null;
    }
    
    public final void guardarIngreso(long fechaMillis, @org.jetbrains.annotations.NotNull()
    java.lang.String concepto, double importe, @org.jetbrains.annotations.NotNull()
    java.lang.String notas) {
    }
    
    private final kotlinx.coroutines.Job insert(eu.villacristina.lechugapro.data.Ingreso ingreso) {
        return null;
    }
    
    private final kotlinx.coroutines.Job update(eu.villacristina.lechugapro.data.Ingreso ingreso) {
        return null;
    }
}