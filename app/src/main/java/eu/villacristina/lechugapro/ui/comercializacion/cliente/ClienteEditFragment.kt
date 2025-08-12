package eu.villacristina.lechugapro.ui.comercializacion.cliente

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import eu.villacristina.lechugapro.data.AppDatabase
import eu.villacristina.lechugapro.data.Cliente
import eu.villacristina.lechugapro.data.ClienteRepository
import eu.villacristina.lechugapro.databinding.FragmentClienteEditBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ClienteEditFragment : Fragment() {

    private var _binding: FragmentClienteEditBinding? = null
    private val binding get() = _binding!!

    // Obtenemos el clienteId de los argumentos de navegación
    private val args: ClienteEditFragmentArgs by navArgs()

    // Pasamos el clienteId al Factory para que el ViewModel sepa qué cargar
    private val viewModel: ClienteEditViewModel by viewModels {
        ClienteEditViewModelFactory(
            ClienteRepository(AppDatabase.getDatabase(requireContext()).clienteDao()),
            args.clienteId
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClienteEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observamos al cliente del ViewModel.
        // Si no es nulo, significa que estamos editando, así que rellenamos los campos.
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.cliente.collectLatest { cliente ->
                cliente?.let {
                    bindCliente(it)
                }
            }
        }

        binding.buttonGuardarCliente.setOnClickListener {
            guardarCliente()
        }
    }

    // Nueva función para rellenar el formulario con los datos del cliente
    private fun bindCliente(cliente: Cliente) {
        binding.editTextNombreCliente.setText(cliente.nombreCompleto)
        binding.editTextTelefonoCliente.setText(cliente.telefono)
        binding.editTextEmailCliente.setText(cliente.email)
        binding.editTextDireccionCliente.setText(cliente.direccion)
        binding.editTextNotasCliente.setText(cliente.notasCliente)

        // Opcional: Cambiamos el título para que el usuario sepa que está editando
        requireActivity().title = "Editar Cliente"
    }

    private fun guardarCliente() {
        val nombre = binding.editTextNombreCliente.text.toString().trim()
        if (nombre.isEmpty()) {
            Toast.makeText(requireContext(), "El nombre del cliente es obligatorio", Toast.LENGTH_SHORT).show()
            return
        }

        // Comprobamos si estamos en modo edición (si el ViewModel tiene un cliente cargado)
        val clienteExistente = viewModel.cliente.value

        if (clienteExistente != null) {
            // MODO EDICIÓN: Creamos una copia del cliente con los datos nuevos y lo actualizamos
            val clienteActualizado = clienteExistente.copy(
                nombreCompleto = nombre,
                telefono = binding.editTextTelefonoCliente.text.toString().trim(),
                email = binding.editTextEmailCliente.text.toString().trim(),
                direccion = binding.editTextDireccionCliente.text.toString().trim(),
                notasCliente = binding.editTextNotasCliente.text.toString().trim()
            )
            viewModel.update(clienteActualizado)
            Toast.makeText(requireContext(), "Cliente actualizado", Toast.LENGTH_SHORT).show()
        } else {
            // MODO CREACIÓN: Creamos un cliente nuevo y lo insertamos
            val nuevoCliente = Cliente(
                nombreCompleto = nombre,
                telefono = binding.editTextTelefonoCliente.text.toString().trim(),
                email = binding.editTextEmailCliente.text.toString().trim(),
                direccion = binding.editTextDireccionCliente.text.toString().trim(),
                notasCliente = binding.editTextNotasCliente.text.toString().trim()
            )
            viewModel.insert(nuevoCliente)
            Toast.makeText(requireContext(), "Cliente guardado", Toast.LENGTH_SHORT).show()
        }

        // Volvemos a la pantalla anterior
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        // Restauramos el título original por si acaso
        requireActivity().title = "LechugaPro"
    }
}