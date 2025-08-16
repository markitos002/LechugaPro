package eu.villacristina.lechugapro.ui.comercializacion.ingreso;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bJ\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000f"}, d2 = {"Leu/villacristina/lechugapro/ui/comercializacion/ingreso/IngresoListaViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Leu/villacristina/lechugapro/data/IngresoRepositoryContract;", "(Leu/villacristina/lechugapro/data/IngresoRepositoryContract;)V", "todosLosIngresos", "Lkotlinx/coroutines/flow/StateFlow;", "", "Leu/villacristina/lechugapro/data/Ingreso;", "getTodosLosIngresos", "()Lkotlinx/coroutines/flow/StateFlow;", "deleteIngreso", "Lkotlinx/coroutines/Job;", "ingreso", "reInsertIngreso", "app_debug"})
public final class IngresoListaViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final eu.villacristina.lechugapro.data.IngresoRepositoryContract repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<eu.villacristina.lechugapro.data.Ingreso>> todosLosIngresos = null;
    
    public IngresoListaViewModel(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.IngresoRepositoryContract repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<eu.villacristina.lechugapro.data.Ingreso>> getTodosLosIngresos() {
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
}