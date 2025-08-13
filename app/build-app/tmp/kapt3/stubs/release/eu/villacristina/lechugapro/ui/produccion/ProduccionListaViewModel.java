package eu.villacristina.lechugapro.ui.produccion;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Leu/villacristina/lechugapro/ui/produccion/ProduccionListaViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Leu/villacristina/lechugapro/data/CicloProduccionRepository;", "(Leu/villacristina/lechugapro/data/CicloProduccionRepository;)V", "ciclos", "Landroidx/lifecycle/LiveData;", "", "Leu/villacristina/lechugapro/data/CicloProduccion;", "getCiclos", "()Landroidx/lifecycle/LiveData;", "Factory", "app_release"})
public final class ProduccionListaViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final eu.villacristina.lechugapro.data.CicloProduccionRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<eu.villacristina.lechugapro.data.CicloProduccion>> ciclos = null;
    
    public ProduccionListaViewModel(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.CicloProduccionRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<eu.villacristina.lechugapro.data.CicloProduccion>> getCiclos() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J%\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\tH\u0016\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Leu/villacristina/lechugapro/ui/produccion/ProduccionListaViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "repository", "Leu/villacristina/lechugapro/data/CicloProduccionRepository;", "(Leu/villacristina/lechugapro/data/CicloProduccionRepository;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "app_release"})
    @kotlin.Suppress(names = {"UNCHECKED_CAST"})
    public static final class Factory implements androidx.lifecycle.ViewModelProvider.Factory {
        @org.jetbrains.annotations.NotNull()
        private final eu.villacristina.lechugapro.data.CicloProduccionRepository repository = null;
        
        public Factory(@org.jetbrains.annotations.NotNull()
        eu.villacristina.lechugapro.data.CicloProduccionRepository repository) {
            super();
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public <T extends androidx.lifecycle.ViewModel>T create(@org.jetbrains.annotations.NotNull()
        java.lang.Class<T> modelClass) {
            return null;
        }
    }
}