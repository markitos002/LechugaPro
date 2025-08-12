package eu.villacristina.lechugapro.ui.comercializacion.ingreso

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
import eu.villacristina.lechugapro.data.IngresoRepository
import eu.villacristina.lechugapro.databinding.FragmentIngresoEditBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class IngresoEditFragment : Fragment() {

    private var _binding: FragmentIngresoEditBinding? = null
    private val binding get() = _binding!!

    private val args: IngresoEditFragmentArgs by navArgs()

    private val viewModel: IngresoEditViewModel by viewModels {
        val database = AppDatabase.getDatabase(requireContext())
        IngresoEditViewModelFactory(
            IngresoRepository(database.ingresoDao()),
            args.clienteId,
            args.ingresoId
        )
    }

    // Formateador para convertir entre Long (timestamp) y String (dd/MM/yyyy)
    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIngresoEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Si estamos editando (ingresoId != -1L), observar y rellenar los campos
        if (args.ingresoId != -1L) {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.ingreso.collectLatest { ingreso ->
                    ingreso?.let {
                        binding.editTextFechaIngreso.setText(dateFormatter.format(Date(it.fecha)))
                        binding.editTextConceptoIngreso.setText(it.concepto)
                        binding.editTextImporteIngreso.setText(it.importe.toString())
                        binding.editTextNotasIngreso.setText(it.notas)
                        requireActivity().title = "Editar Ingreso"
                    }
                }
            }
        } else {
            requireActivity().title = "Nuevo Ingreso"
            // Opcional: Poner la fecha actual por defecto al crear uno nuevo
            binding.editTextFechaIngreso.setText(dateFormatter.format(Date()))
        }

        binding.buttonGuardarIngreso.setOnClickListener {
            guardarIngreso()
        }
    }

    private fun guardarIngreso() {
        val fechaStr = binding.editTextFechaIngreso.text.toString().trim()
        val concepto = binding.editTextConceptoIngreso.text.toString().trim()
        val importeStr = binding.editTextImporteIngreso.text.toString().trim()
        val notas = binding.editTextNotasIngreso.text.toString().trim()

        if (fechaStr.isEmpty() || concepto.isEmpty() || importeStr.isEmpty()) {
            Toast.makeText(requireContext(), "Fecha, Concepto e Importe son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        val fechaLong: Long
        try {
            fechaLong = dateFormatter.parse(fechaStr)?.time ?: run {
                Toast.makeText(requireContext(), "Formato de fecha inválido (dd/MM/yyyy)", Toast.LENGTH_SHORT).show()
                return
            }
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Formato de fecha inválido (dd/MM/yyyy)", Toast.LENGTH_SHORT).show()
            return
        }

        val importeDouble: Double
        try {
            importeDouble = importeStr.toDouble()
            if (importeDouble <= 0) {
                Toast.makeText(requireContext(), "El importe debe ser mayor que cero", Toast.LENGTH_SHORT).show()
                return
            }
        } catch (e: NumberFormatException) {
            Toast.makeText(requireContext(), "Importe inválido", Toast.LENGTH_SHORT).show()
            return
        }

        viewModel.guardarIngreso(fechaLong, concepto, importeDouble, notas)

        Toast.makeText(requireContext(), "Ingreso guardado", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp() // Volver a la pantalla anterior
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        // Restaurar el título original si es necesario (ej. "Detalle del Cliente")
        // Esto dependerá de cómo se gestione el título en la pantalla anterior.
    }
}
