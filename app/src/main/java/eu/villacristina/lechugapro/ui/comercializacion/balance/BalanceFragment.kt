
package eu.villacristina.lechugapro.ui.comercializacion.balance

import eu.villacristina.lechugapro.ui.comercializacion.balance.BalancePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import eu.villacristina.lechugapro.data.AppDatabase
import eu.villacristina.lechugapro.data.IngresoRepository
import eu.villacristina.lechugapro.databinding.FragmentBalanceBinding
import eu.villacristina.lechugapro.ui.comercializacion.ingreso.IngresoListaAdapter
import eu.villacristina.lechugapro.ui.comercializacion.ingreso.IngresoListaViewModel
import eu.villacristina.lechugapro.ui.comercializacion.ingreso.IngresoListaViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BalanceFragment : Fragment() {
	private var _binding: FragmentBalanceBinding? = null
	private val binding get() = _binding!!
	private val viewModel: IngresoListaViewModel by viewModels {
		val database = AppDatabase.getDatabase(requireContext())
		IngresoListaViewModelFactory(IngresoRepository(database.ingresoDao()))
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = FragmentBalanceBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		// Configurar TabLayout y ViewPager2 para las tres pestañas: Ingresos, Gastos, Ganancia
		val tabLayout = binding.tabLayoutBalance
		val viewPager = binding.viewpagerBalance

		val tabTitles = listOf("Ingresos", "Gastos", "Ganancia")
		viewPager.adapter = BalancePagerAdapter(requireActivity())
		TabLayoutMediator(tabLayout, viewPager) { tab, position ->
			tab.text = tabTitles[position]
		}.attach()
	}

	override fun onDestroyView() { super.onDestroyView(); _binding = null }
}

