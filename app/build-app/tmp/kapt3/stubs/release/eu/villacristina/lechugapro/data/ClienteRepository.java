package eu.villacristina.lechugapro.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\u000eJ\u0018\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0016\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Leu/villacristina/lechugapro/data/ClienteRepository;", "Leu/villacristina/lechugapro/data/ClienteRepositoryContract;", "clienteDao", "Leu/villacristina/lechugapro/data/ClienteDao;", "(Leu/villacristina/lechugapro/data/ClienteDao;)V", "todosLosClientes", "Lkotlinx/coroutines/flow/Flow;", "", "Leu/villacristina/lechugapro/data/Cliente;", "getTodosLosClientes", "()Lkotlinx/coroutines/flow/Flow;", "delete", "", "cliente", "(Leu/villacristina/lechugapro/data/Cliente;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getClienteById", "id", "", "insert", "update", "app_release"})
public final class ClienteRepository implements eu.villacristina.lechugapro.data.ClienteRepositoryContract {
    @org.jetbrains.annotations.NotNull()
    private final eu.villacristina.lechugapro.data.ClienteDao clienteDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<eu.villacristina.lechugapro.data.Cliente>> todosLosClientes = null;
    
    public ClienteRepository(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.ClienteDao clienteDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<eu.villacristina.lechugapro.data.Cliente>> getTodosLosClientes() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<eu.villacristina.lechugapro.data.Cliente> getClienteById(long id) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.Cliente cliente, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object update(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.Cliente cliente, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    eu.villacristina.lechugapro.data.Cliente cliente, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}