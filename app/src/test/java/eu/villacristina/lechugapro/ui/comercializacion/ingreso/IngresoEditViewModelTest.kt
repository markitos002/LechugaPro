package eu.villacristina.lechugapro.ui.comercializacion.ingreso

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

/**
 * Pruebas unitarias simples para la lógica de guardado en IngresoEditViewModel.
 * Se usa un fake repository en memoria (solo métodos necesarios).
 */
@OptIn(ExperimentalCoroutinesApi::class)
class IngresoEditViewModelTest {

    private lateinit var fakeRepo: FakeIngresoRepository
    private lateinit var viewModel: IngresoEditViewModel

    private val testDispatcher = StandardTestDispatcher()
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        fakeRepo = FakeIngresoRepository()
        viewModel = IngresoEditViewModel(fakeRepo, clienteId = 5L, ingresoId = -1L)
    }

    @org.junit.After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `crear nuevo ingreso lo inserta en el repositorio`() = runTest(testDispatcher) {
        viewModel.guardarIngreso(fechaMillis = 1000L, concepto = "Venta prueba", importe = 25.5, notas = "")
        advanceUntilIdle()
        val lista = fakeRepo.data.first()
        assertEquals(1, lista.size)
        assertEquals("Venta prueba", lista[0].concepto)
        assertEquals(25.5, lista[0].importe, 0.0)
        assertEquals(5L, lista[0].idCliente)
    }

    @Test
    fun `editar ingreso actualiza campos`() = runTest(testDispatcher) {
        // Insertar uno inicial
        val inicial = Ingreso(id = 1L, idCliente = 5L, fecha = 10L, concepto = "Viejo", importe = 1.0, notas = null)
        fakeRepo.insert(inicial)
        // Crear VM en modo edición
    val vmEdit = IngresoEditViewModel(fakeRepo, clienteId = 5L, ingresoId = 1L)
    // Esperar a que cargue el ingreso existente en el init
    advanceUntilIdle()
        // Actualizar
        vmEdit.guardarIngreso(fechaMillis = 20L, concepto = "Nuevo", importe = 3.5, notas = "nota")
        advanceUntilIdle()
        val lista = fakeRepo.data.first()
        assertEquals(1, lista.size)
        assertEquals("Nuevo", lista[0].concepto)
        assertEquals(20L, lista[0].fecha)
    }

    // Fake sencillo en memoria
    class FakeIngresoRepository : eu.villacristina.lechugapro.data.IngresoRepositoryContract {
        val data = MutableStateFlow<List<Ingreso>>(emptyList())
        private var nextId = 1L
        override val todosLosIngresos: Flow<List<Ingreso>> get() = data
        override fun getIngresosByClienteId(clienteId: Long): Flow<List<Ingreso>> =
            MutableStateFlow(data.value.filter { it.idCliente == clienteId })
        override suspend fun insert(ingreso: Ingreso) {
            val withId = if (ingreso.id == 0L) ingreso.copy(id = nextId++) else ingreso
            data.value = data.value + withId
        }
        override suspend fun update(ingreso: Ingreso) {
            data.value = data.value.map { if (it.id == ingreso.id) ingreso else it }
        }
        override fun getIngresoById(id: Long): Flow<Ingreso?> = MutableStateFlow(data.value.find { it.id == id })
        override suspend fun delete(ingreso: Ingreso) {
            data.value = data.value.filterNot { it.id == ingreso.id }
        }
    }
}

