package eu.villacristina.lechugapro.ui.inicio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import eu.villacristina.lechugapro.R

class InicioFragment : Fragment() {
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		return inflater.inflate(R.layout.fragment_inicio, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		// Load SVG logo from raw/icon.svg using Coil
		val imageLoader = ImageLoader.Builder(requireContext())
			.components { add(SvgDecoder.Factory()) }
			.build()
		view.findViewById<ImageView>(R.id.logo).load(R.raw.icon, imageLoader) {
			decoderFactory(SvgDecoder.Factory())
			crossfade(true)
		}

		view.findViewById<View>(R.id.btn_clientes).setOnClickListener {
			findNavController().navigate(R.id.clienteListaFragment)
		}
		view.findViewById<View>(R.id.btn_ingresos).setOnClickListener {
			findNavController().navigate(R.id.ingresoListaFragment)
		}
		view.findViewById<View>(R.id.btn_produccion).setOnClickListener {
			findNavController().navigate(R.id.produccionListaFragment)
		}
	}
}

