package eu.villacristina.lechugapro.ui.comercializacion.cliente

import android.os.Bundle
import androidx.navigation.NavDirections
import eu.villacristina.lechugapro.R
import kotlin.Int
import kotlin.Long

public class ClienteListaFragmentDirections private constructor() {
  private data class ActionClienteListaFragmentToClienteEditFragment(
    public val clienteId: Long = -1L,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_clienteListaFragment_to_clienteEditFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("clienteId", this.clienteId)
        return result
      }
  }

  private data class ActionClienteListaFragmentToClienteDetalleFragment(
    public val clienteId: Long,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_clienteListaFragment_to_clienteDetalleFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("clienteId", this.clienteId)
        return result
      }
  }

  public companion object {
    public fun actionClienteListaFragmentToClienteEditFragment(clienteId: Long = -1L): NavDirections
        = ActionClienteListaFragmentToClienteEditFragment(clienteId)

    public fun actionClienteListaFragmentToClienteDetalleFragment(clienteId: Long): NavDirections =
        ActionClienteListaFragmentToClienteDetalleFragment(clienteId)
  }
}
