package eu.villacristina.lechugapro.ui.produccion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import eu.villacristina.lechugapro.R
import eu.villacristina.lechugapro.data.CicloProduccion
import eu.villacristina.lechugapro.data.CicloProduccionRepository
import eu.villacristina.lechugapro.databinding.FragmentProduccionEditBinding
import eu.villacristina.lechugapro.LechugaProApplication
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProduccionEditFragment : Fragment() {

    private var _binding: FragmentProduccionEditBinding? = null
    private val binding get() = _binding!!

    private val args: ProduccionEditFragmentArgs by navArgs()
    private val viewModel: ProduccionEditViewModel by viewModels {
        val application = requireActivity().application as LechugaProApplication
        val cicloProduccionDao = application.database.cicloProduccionDao()
        ProduccionEditViewModelFactory(
            CicloProduccionRepository(cicloProduccionDao),
            args.cicloId
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProduccionEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar el AutoCompleteTextView para el estado
        val estados = resources.getStringArray(R.array.estados_produccion)
        val adapterEstado = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, estados)
        (binding.editTextEstado.editText as? AutoCompleteTextView)?.setAdapter(adapterEstado)

        // Observar el ciclo existente para rellenar los campos si estamos editando
        if (args.cicloId != 0L) {
            viewModel.ciclo.observe(viewLifecycleOwner) { ciclo ->
                ciclo?.let { bindCiclo(it) }
            }
        }

        binding.buttonGuardar.setOnClickListener {
            guardarCiclo()
        }
    }

    private fun bindCiclo(ciclo: CicloProduccion) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        binding.editTextNombreCiclo.editText?.setText(ciclo.nombreCiclo)
        binding.editTextEstado.editText?.setText(ciclo.estado)
        binding.editTextNumeroPlantas.editText?.setText(ciclo.numeroPlantas.toString())
        binding.editTextFechaSiembra.editText?.setText(ciclo.fechaSiembra?.let { dateFormat.format(Date(it)) })
        binding.editTextNotas.editText?.setText(ciclo.notas)
    }

    private fun guardarCiclo() {
        val nombreCicloText = binding.editTextNombreCiclo.editText?.text.toString().trim()
        if (nombreCicloText.isEmpty()) {
            binding.editTextNombreCiclo.error = "El nombre del ciclo es obligatorio."
            return
        }

        val estado = binding.editTextEstado.editText?.text.toString()
        val numeroPlantas = binding.editTextNumeroPlantas.editText?.text.toString().toIntOrNull() ?: 0
        val fechaSiembraString = binding.editTextFechaSiembra.editText?.text.toString()
        val notas = binding.editTextNotas.editText?.text.toString()

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val fechaSiembraMillis = try {
            if (fechaSiembraString.isNotEmpty()) dateFormat.parse(fechaSiembraString)?.time else null
        } catch (e: Exception) {
            null
        }

        // Distinguir claramente entre insertar uno nuevo y actualizar uno existente
        if (args.cicloId == 0L) {
            // Es un ciclo nuevo: crear objeto sin ID y llamar a INSERT
            val nuevoCiclo = CicloProduccion(
                nombreCiclo = nombreCicloText,
                estado = estado,
                numeroPlantas = numeroPlantas,
                fechaSiembra = fechaSiembraMillis,
                fechaEstimadaCosecha = null, // Se calculará o establecerá más adelante
                notas = notas
            )
            viewModel.insert(nuevoCiclo)
            Toast.makeText(requireContext(), "Ciclo de producción guardado.", Toast.LENGTH_SHORT).show()
        } else {
            // Es un ciclo existente: crear objeto CON ID y llamar a UPDATE
            val cicloActualizado = CicloProduccion(
                id = args.cicloId,
                nombreCiclo = nombreCicloText,
                estado = estado,
                numeroPlantas = numeroPlantas,
                fechaSiembra = fechaSiembraMillis,
                fechaEstimadaCosecha = null, // Se calculará o establecerá más adelante
                notas = notas
            )
            viewModel.update(cicloActualizado)
            Toast.makeText(requireContext(), "Ciclo de producción actualizado.", Toast.LENGTH_SHORT).show()
        }
        
        findNavController().popBackStack() // Volver a la pantalla anterior
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
