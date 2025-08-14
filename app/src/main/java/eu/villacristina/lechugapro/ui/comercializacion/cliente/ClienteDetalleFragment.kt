package eu.villacristina.lechugapro.ui.comercializacion.cliente

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import eu.villacristina.lechugapro.R
import eu.villacristina.lechugapro.data.AppDatabase
import eu.villacristina.lechugapro.data.ClienteRepository
import eu.villacristina.lechugapro.data.ClienteRepositoryContract
import eu.villacristina.lechugapro.data.IngresoRepository
import eu.villacristina.lechugapro.data.IngresoRepositoryContract
import eu.villacristina.lechugapro.databinding.FragmentClienteDetalleBinding
import eu.villacristina.lechugapro.ui.comercializacion.ingreso.IngresoListaAdapter
import eu.villacristina.lechugapro.util.CurrencyFormatter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import android.content.Intent
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ClienteDetalleFragment : Fragment() {
    private var _binding: FragmentClienteDetalleBinding? = null
    private val binding get() = _binding!!
    private val args: ClienteDetalleFragmentArgs by navArgs()

    private val viewModel: ClienteDetalleViewModel by viewModels {
        val database = AppDatabase.getDatabase(requireContext())
        val clienteRepo: ClienteRepositoryContract = ClienteRepository(database.clienteDao())
        val ingresoRepo: IngresoRepositoryContract = IngresoRepository(database.ingresoDao())
        ClienteDetalleViewModelFactory(clienteRepo, ingresoRepo, args.clienteId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) { menuInflater.inflate(R.menu.menu_detalle, menu) }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean = when (menuItem.itemId) {
                R.id.action_edit -> {
                    val action = eu.villacristina.lechugapro.ui.comercializacion.cliente.ClienteDetalleFragmentDirections.actionClienteDetalleFragmentToClienteEditFragment(args.clienteId)
                    findNavController().navigate(action); true }
                R.id.action_delete -> { mostrarDialogoBorrarCliente(); true }
                R.id.action_export_csv -> { exportarCsv(); true }
                else -> false
            }
        }, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentClienteDetalleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ingresoAdapter = IngresoListaAdapter { ingreso ->
            val action = eu.villacristina.lechugapro.ui.comercializacion.cliente.ClienteDetalleFragmentDirections.actionClienteDetalleFragmentToIngresoEditFragment(
                clienteId = args.clienteId,
                ingresoId = ingreso.id
            )
            findNavController().navigate(action)
        }
        binding.recyclerviewIngresos.adapter = ingresoAdapter
        binding.recyclerviewIngresos.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerviewIngresos.isNestedScrollingEnabled = false
        val swipeCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) = false
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return
                val ingreso = ingresoAdapter.currentList.getOrNull(position)
                if (ingreso != null) {
                    viewModel.deleteIngreso(ingreso)
                    Snackbar.make(binding.root, getString(R.string.ingreso_borrado), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.deshacer)) { viewModel.reInsertIngreso(ingreso) }
                        .show()
                }
            }
        }
        ItemTouchHelper(swipeCallback).attachToRecyclerView(binding.recyclerviewIngresos)
        viewLifecycleOwner.lifecycleScope.launch { viewModel.cliente.collectLatest { it?.let { bindCliente(it) } } }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.ingresos.collectLatest {
                ingresoAdapter.submitList(it)
                val total = it.sumOf { ing -> ing.importe }
                binding.detalleTotalIngresos.text = "Total vendido: ${CurrencyFormatter.formatPeso(total)}"
            }
        }
        binding.fabAnadirIngreso.setOnClickListener {
            val action = eu.villacristina.lechugapro.ui.comercializacion.cliente.ClienteDetalleFragmentDirections.actionClienteDetalleFragmentToIngresoEditFragment(
                clienteId = args.clienteId,
                ingresoId = -1L
            )
            findNavController().navigate(action)
        }

    binding.btnCompartirIngresos.setOnClickListener { exportarCsv() }
    }

    private fun bindCliente(cliente: eu.villacristina.lechugapro.data.Cliente) {
        requireActivity().title = cliente.nombreCompleto
        binding.detalleNombreCliente.text = cliente.nombreCompleto
        binding.detalleTelefonoCliente.text = cliente.telefono.takeIf { !it.isNullOrBlank() } ?: "No especificado"
        binding.detalleEmailCliente.text = cliente.email.takeIf { !it.isNullOrBlank() } ?: "No especificado"
        binding.detalleDireccionCliente.text = cliente.direccion.takeIf { !it.isNullOrBlank() } ?: "No especificada"
        binding.detalleNotasCliente.text = cliente.notasCliente.takeIf { !it.isNullOrBlank() } ?: "Sin notas"
    }

    private fun mostrarDialogoBorrarCliente() {
        val clienteActual = viewModel.cliente.value ?: return
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.confirm_delete_cliente_title))
            .setMessage(getString(R.string.confirm_delete_cliente_message, clienteActual.nombreCompleto))
            .setPositiveButton(getString(R.string.eliminar)) { _, _ ->
                viewModel.deleteCliente()
                Toast.makeText(requireContext(), getString(R.string.cliente_eliminado), Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
            .setNegativeButton(getString(R.string.cancelar), null)
            .show()
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }

    private fun exportarCsv() {
        val cliente = viewModel.cliente.value ?: return
        val ingresos = viewModel.ingresos.value
        // Generar CSV en cache
    try {
            val cacheDir = File(requireContext().cacheDir, "exports")
            if (!cacheDir.exists()) cacheDir.mkdirs()
            val safeName = cliente.nombreCompleto.replace("[^A-Za-z0-9_-]".toRegex(), "_")
            val file = File(cacheDir, "ingresos_${safeName}.csv")
            FileWriter(file, false).use { fw ->
        // Primera línea: encabezado con el nombre del cliente
        fw.appendLine("Cliente: ${cliente.nombreCompleto}")
        // Segunda línea: cabecera de columnas (incluye importe plano y formateado)
                fw.appendLine("Fecha,Concepto,Importe,Importe formateado,Estado pago,Notas")
                val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                ingresos.forEach { ing ->
                    val fecha = df.format(Date(ing.fecha))
                    // Para CSV usar número plano y una columna con el formateo amigable también
                    val importePlano = ing.importe
                    val importeTxt = CurrencyFormatter.formatPeso(ing.importe)
                    val concepto = ing.concepto.replace(",", " ")
                    val estado = ing.estadoPago.replace(",", " ")
                    val notas = (ing.notas ?: "").replace(",", " ")
                    fw.appendLine("$fecha,$concepto,$importePlano,$importeTxt,$estado,$notas")
                }
            }
            val uri = FileProvider.getUriForFile(requireContext(), requireContext().packageName + ".fileprovider", file)
            val share = Intent(Intent.ACTION_SEND).apply {
                type = "text/csv"
                putExtra(Intent.EXTRA_STREAM, uri)
                putExtra(Intent.EXTRA_SUBJECT, "Ingresos ${cliente.nombreCompleto}")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            startActivity(Intent.createChooser(share, "Compartir CSV"))
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Error al exportar CSV", Toast.LENGTH_SHORT).show()
        }
    }
}