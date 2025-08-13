package eu.villacristina.lechugapro.ui.comercializacion.ingreso;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J$\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010#\u001a\u00020\u001aH\u0016J\u001a\u0010$\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020\u001c2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J+\u0010&\u001a\u00020\u001a2\b\u0010\'\u001a\u0004\u0018\u00010\u00112\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u001a0)H\u0002\u00a2\u0006\u0002\u0010*R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0012R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006+"}, d2 = {"Leu/villacristina/lechugapro/ui/comercializacion/ingreso/IngresoEditFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Leu/villacristina/lechugapro/databinding/FragmentIngresoEditBinding;", "args", "Leu/villacristina/lechugapro/ui/comercializacion/ingreso/IngresoEditFragmentArgs;", "getArgs", "()Leu/villacristina/lechugapro/ui/comercializacion/ingreso/IngresoEditFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "binding", "getBinding", "()Leu/villacristina/lechugapro/databinding/FragmentIngresoEditBinding;", "dateFormatter", "Ljava/text/SimpleDateFormat;", "selectedFecha", "", "Ljava/lang/Long;", "viewModel", "Leu/villacristina/lechugapro/ui/comercializacion/ingreso/IngresoEditViewModel;", "getViewModel", "()Leu/villacristina/lechugapro/ui/comercializacion/ingreso/IngresoEditViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "guardarIngreso", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "showDatePicker", "initialSelection", "onSelected", "Lkotlin/Function1;", "(Ljava/lang/Long;Lkotlin/jvm/functions/Function1;)V", "app_release"})
public final class IngresoEditFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private eu.villacristina.lechugapro.databinding.FragmentIngresoEditBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final androidx.navigation.NavArgsLazy args$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.text.SimpleDateFormat dateFormatter = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Long selectedFecha;
    
    public IngresoEditFragment() {
        super();
    }
    
    private final eu.villacristina.lechugapro.databinding.FragmentIngresoEditBinding getBinding() {
        return null;
    }
    
    private final eu.villacristina.lechugapro.ui.comercializacion.ingreso.IngresoEditFragmentArgs getArgs() {
        return null;
    }
    
    private final eu.villacristina.lechugapro.ui.comercializacion.ingreso.IngresoEditViewModel getViewModel() {
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
    
    private final void guardarIngreso() {
    }
    
    private final void showDatePicker(java.lang.Long initialSelection, kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onSelected) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}