package eu.villacristina.lechugapro.ui.comercializacion.ingreso

import eu.villacristina.lechugapro.data.Ingreso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class IngresoListaViewModelTest {
    private lateinit var repo: FakeRepo
    private lateinit var viewModel: IngresoListaViewModel
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        repo = FakeRepo()
        viewModel = IngresoListaViewModel(repo)
    }

    @org.junit.After
    fun tearDown() { Dispatchers.resetMain() }

    @Test
    fun `delete elimina ingreso y reInsert lo recupera`() = runTest(dispatcher) {
        // Insertar dos ingresos
        repo.insert(Ingreso(id = 1, idCliente = 9, fecha = 10L, concepto = "A", importe = 1.0))
        repo.insert(Ingreso(id = 2, idCliente = 9, fecha = 20L, concepto = "B", importe = 2.0))
    advanceUntilIdle()
    // Como el StateFlow en ViewModel usa WhileSubscribed, verificamos directamente el repo
    assertEquals(2, repo.data.value.size)
        // Borrar uno
        viewModel.deleteIngreso(repo.data.value.first { it.id == 1L })
        advanceUntilIdle()
    assertEquals(1, repo.data.value.size)
        // Undo
        val deleted = Ingreso(id = 1, idCliente = 9, fecha = 10L, concepto = "A", importe = 1.0)
        viewModel.reInsertIngreso(deleted)
        advanceUntilIdle()
    assertEquals(2, repo.data.value.size)
    }

    class FakeRepo : eu.villacristina.lechugapro.data.IngresoRepositoryContract {
        val data = MutableStateFlow<List<Ingreso>>(emptyList())
        override val todosLosIngresos = data
        override fun getIngresosByClienteId(clienteId: Long) = MutableStateFlow(data.value.filter { it.idCliente == clienteId })
        override fun getIngresoById(id: Long) = MutableStateFlow(data.value.find { it.id == id })
        override suspend fun insert(ingreso: Ingreso) { data.value = data.value + ingreso }
        override suspend fun update(ingreso: Ingreso) { data.value = data.value.map { if (it.id == ingreso.id) ingreso else it } }
        override suspend fun delete(ingreso: Ingreso) { data.value = data.value.filterNot { it.id == ingreso.id } }
    }
}
