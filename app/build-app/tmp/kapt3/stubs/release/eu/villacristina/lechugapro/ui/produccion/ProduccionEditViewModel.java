package eu.villacristina.lechugapro.ui.produccion;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0015B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J4\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2$\b\u0002\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\r0\u0010J,\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\u001c\b\u0002\u0010\u000f\u001a\u0016\u0012\u0004\u0012\u00020\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0012\u0004\u0012\u00020\r0\u0014R\u0019\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Leu/villacristina/lechugapro/ui/produccion/ProduccionEditViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Leu/villacristina/lechugapro/data/CicloProduccionRepository;", "cicloId", "", "(Leu/villacristina/lechugapro/data/CicloProduccionRepository;J)V", "ciclo", "Landroidx/lifecycle/LiveData;", "Leu/villacristina/lechugapro/data/CicloProduccion;", "getCiclo", "()Landroidx/lifecycle/LiveData;", "insert", "", "c", "onComplete", "Lkotlin/Function3;", "", "", "update", "Lkotlin/Function2;", "Factory", "app_release"})
public final class ProduccionEditViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final eu.villacristina.lechugapro.data.CicloProduccionRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<eu.villacristina.lechugapro.data.CicloProduccion> ciclo = null;
    
    public ProduccionEditViewModel(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.CicloProduccionRepository repository, long cicloId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<eu.villacristina.lechugapro.data.CicloProduccion> getCiclo() {
        return null;
    }
    
    public final void insert(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.CicloProduccion c, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function3<? super java.lang.Boolean, ? super java.lang.String, ? super java.lang.Long, kotlin.Unit> onComplete) {
    }
    
    public final void update(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.CicloProduccion c, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.Boolean, ? super java.lang.String, kotlin.Unit> onComplete) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J%\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000bH\u0016\u00a2\u0006\u0002\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Leu/villacristina/lechugapro/ui/produccion/ProduccionEditViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "repo", "Leu/villacristina/lechugapro/data/CicloProduccionRepository;", "id", "", "(Leu/villacristina/lechugapro/data/CicloProduccionRepository;J)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "app_release"})
    public static final class Factory implements androidx.lifecycle.ViewModelProvider.Factory {
        @org.jetbrains.annotations.NotNull()
        private final eu.villacristina.lechugapro.data.CicloProduccionRepository repo = null;
        private final long id = 0L;
        
        public Factory(@org.jetbrains.annotations.NotNull()
        eu.villacristina.lechugapro.data.CicloProduccionRepository repo, long id) {
            super();
        }
        
        @java.lang.Override()
        @kotlin.Suppress(names = {"UNCHECKED_CAST"})
        @org.jetbrains.annotations.NotNull()
        public <T extends androidx.lifecycle.ViewModel>T create(@org.jetbrains.annotations.NotNull()
        java.lang.Class<T> modelClass) {
            return null;
        }
    }
}