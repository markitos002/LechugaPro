package eu.villacristina.lechugapro.ui.comercializacion.cliente

import android.os.Bundle
import androidx.navigation.NavDirections
import eu.villacristina.lechugapro.R
import kotlin.Int
import kotlin.Long

public class ClienteDetalleFragmentDirections private constructor() {
  private data class ActionClienteDetalleFragmentToClienteEditFragment(
    public val clienteId: Long = -1L,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_clienteDetalleFragment_to_clienteEditFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("clienteId", this.clienteId)
        return result
      }
  }

  private data class ActionClienteDetalleFragmentToIngresoEditFragment(
    public val clienteId: Long,
    public val ingresoId: Long = -1L,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_clienteDetalleFragment_to_ingresoEditFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("ingresoId", this.ingresoId)
        result.putLong("clienteId", this.clienteId)
        return result
      }
  }

  public companion object {
    public fun actionClienteDetalleFragmentToClienteEditFragment(clienteId: Long = -1L):
        NavDirections = ActionClienteDetalleFragmentToClienteEditFragment(clienteId)

    public fun actionClienteDetalleFragmentToIngresoEditFragment(clienteId: Long, ingresoId: Long
        = -1L): NavDirections = ActionClienteDetalleFragmentToIngresoEditFragment(clienteId,
        ingresoId)
  }
}
