package eu.villacristina.lechugapro.ui.produccion;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J$\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\u001a\u0010(\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020!2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006*"}, d2 = {"Leu/villacristina/lechugapro/ui/produccion/ProduccionDetalleFragment;", "Landroidx/fragment/app/Fragment;", "()V", "args", "Leu/villacristina/lechugapro/ui/produccion/ProduccionDetalleFragmentArgs;", "getArgs", "()Leu/villacristina/lechugapro/ui/produccion/ProduccionDetalleFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "btnEditar", "Landroid/widget/Button;", "btnIniciar", "btnTerminar", "df", "Ljava/text/SimpleDateFormat;", "estado", "Landroid/widget/TextView;", "fechas", "nombre", "notas", "numero", "variedad", "viewModel", "Leu/villacristina/lechugapro/ui/produccion/ProduccionDetalleViewModel;", "getViewModel", "()Leu/villacristina/lechugapro/ui/produccion/ProduccionDetalleViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "bind", "", "c", "Leu/villacristina/lechugapro/data/CicloProduccion;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "app_debug"})
public final class ProduccionDetalleFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.NotNull()
    private final androidx.navigation.NavArgsLazy args$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private android.widget.TextView nombre;
    private android.widget.TextView estado;
    private android.widget.TextView variedad;
    private android.widget.TextView numero;
    private android.widget.TextView fechas;
    private android.widget.TextView notas;
    private android.widget.Button btnIniciar;
    private android.widget.Button btnTerminar;
    private android.widget.Button btnEditar;
    @org.jetbrains.annotations.NotNull()
    private final java.text.SimpleDateFormat df = null;
    
    public ProduccionDetalleFragment() {
        super();
    }
    
    private final eu.villacristina.lechugapro.ui.produccion.ProduccionDetalleFragmentArgs getArgs() {
        return null;
    }
    
    private final eu.villacristina.lechugapro.ui.produccion.ProduccionDetalleViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void bind(eu.villacristina.lechugapro.data.CicloProduccion c) {
    }
}