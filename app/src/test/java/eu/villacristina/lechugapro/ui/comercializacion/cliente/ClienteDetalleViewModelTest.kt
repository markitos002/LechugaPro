package eu.villacristina.lechugapro.ui.comercializacion.cliente

import eu.villacristina.lechugapro.data.Cliente
import eu.villacristina.lechugapro.data.Ingreso
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ClienteDetalleViewModelTest {

    private lateinit var clienteRepo: FakeClienteRepo
    private lateinit var ingresoRepo: FakeIngresoRepo
    private lateinit var viewModel: ClienteDetalleViewModel

    private val testDispatcher = StandardTestDispatcher()
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        clienteRepo = FakeClienteRepo()
        ingresoRepo = FakeIngresoRepo()
        val cliente = Cliente(id = 10L, nombreCompleto = "Test", telefono = null, email = null, direccion = null, notasCliente = null)
        clienteRepo.data.value = listOf(cliente)
        ingresoRepo.data.value = listOf(
            Ingreso(id = 1L, idCliente = 10L, fecha = 100L, concepto = "A", importe = 10.0, notas = null),
            Ingreso(id = 2L, idCliente = 10L, fecha = 200L, concepto = "B", importe = 20.0, notas = null)
        )
        viewModel = ClienteDetalleViewModel(clienteRepo, ingresoRepo, 10L)
    }

    @org.junit.After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `carga cliente y sus ingresos`() = runTest(testDispatcher) {
        advanceUntilIdle()
        val c = viewModel.cliente.first()
        val ingresos = viewModel.ingresos.first()
        assertEquals(10L, c?.id)
        assertEquals(2, ingresos.size)
    }

    class FakeClienteRepo : eu.villacristina.lechugapro.data.ClienteRepositoryContract {
        val data = MutableStateFlow<List<Cliente>>(emptyList())
        override val todosLosClientes: Flow<List<Cliente>> get() = data
        override fun getClienteById(id: Long): Flow<Cliente?> = MutableStateFlow(data.value.find { it.id == id })
        override suspend fun insert(cliente: Cliente) { data.value = data.value + cliente }
        override suspend fun update(cliente: Cliente) { data.value = data.value.map { if (it.id == cliente.id) cliente else it } }
        override suspend fun delete(cliente: Cliente) { data.value = data.value.filterNot { it.id == cliente.id } }
    }
    class FakeIngresoRepo : eu.villacristina.lechugapro.data.IngresoRepositoryContract {
        val data = MutableStateFlow<List<Ingreso>>(emptyList())
        override val todosLosIngresos: Flow<List<Ingreso>> get() = data
        override fun getIngresosByClienteId(clienteId: Long): Flow<List<Ingreso>> =
            MutableStateFlow(data.value.filter { it.idCliente == clienteId })
        override fun getIngresoById(id: Long): Flow<Ingreso?> = MutableStateFlow(data.value.find { it.id == id })
        override suspend fun insert(ingreso: Ingreso) { data.value = data.value + ingreso }
        override suspend fun update(ingreso: Ingreso) { data.value = data.value.map { if (it.id == ingreso.id) ingreso else it } }
        override suspend fun delete(ingreso: Ingreso) { data.value = data.value.filterNot { it.id == ingreso.id } }
    }
}

