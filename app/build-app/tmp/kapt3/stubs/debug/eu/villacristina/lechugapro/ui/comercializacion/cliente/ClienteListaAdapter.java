package eu.villacristina.lechugapro.ui.comercializacion.cliente;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00112\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0010\u0011B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Leu/villacristina/lechugapro/ui/comercializacion/cliente/ClienteListaAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Leu/villacristina/lechugapro/data/Cliente;", "Leu/villacristina/lechugapro/ui/comercializacion/cliente/ClienteListaAdapter$ClienteViewHolder;", "onItemClicked", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ClienteViewHolder", "Companion", "app_debug"})
public final class ClienteListaAdapter extends androidx.recyclerview.widget.ListAdapter<eu.villacristina.lechugapro.data.Cliente, eu.villacristina.lechugapro.ui.comercializacion.cliente.ClienteListaAdapter.ClienteViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<eu.villacristina.lechugapro.data.Cliente, kotlin.Unit> onItemClicked = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.recyclerview.widget.DiffUtil.ItemCallback<eu.villacristina.lechugapro.data.Cliente> DiffCallback = null;
    @org.jetbrains.annotations.NotNull()
    public static final eu.villacristina.lechugapro.ui.comercializacion.cliente.ClienteListaAdapter.Companion Companion = null;
    
    public ClienteListaAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super eu.villacristina.lechugapro.data.Cliente, kotlin.Unit> onItemClicked) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public eu.villacristina.lechugapro.ui.comercializacion.cliente.ClienteListaAdapter.ClienteViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.ui.comercializacion.cliente.ClienteListaAdapter.ClienteViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Leu/villacristina/lechugapro/ui/comercializacion/cliente/ClienteListaAdapter$ClienteViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Leu/villacristina/lechugapro/databinding/ItemClienteBinding;", "(Leu/villacristina/lechugapro/databinding/ItemClienteBinding;)V", "bind", "", "cliente", "Leu/villacristina/lechugapro/data/Cliente;", "app_debug"})
    public static final class ClienteViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final eu.villacristina.lechugapro.databinding.ItemClienteBinding binding = null;
        
        public ClienteViewHolder(@org.jetbrains.annotations.NotNull()
        eu.villacristina.lechugapro.databinding.ItemClienteBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        eu.villacristina.lechugapro.data.Cliente cliente) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Leu/villacristina/lechugapro/ui/comercializacion/cliente/ClienteListaAdapter$Companion;", "", "()V", "DiffCallback", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Leu/villacristina/lechugapro/data/Cliente;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}