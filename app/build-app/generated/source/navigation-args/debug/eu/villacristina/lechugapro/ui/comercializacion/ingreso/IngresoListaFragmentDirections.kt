package eu.villacristina.lechugapro.ui.comercializacion.ingreso

import android.os.Bundle
import androidx.navigation.NavDirections
import eu.villacristina.lechugapro.R
import kotlin.Int
import kotlin.Long

public class IngresoListaFragmentDirections private constructor() {
  private data class ActionIngresoListaFragmentToIngresoEditFragment(
    public val clienteId: Long,
    public val ingresoId: Long = -1L,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_ingresoListaFragment_to_ingresoEditFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("ingresoId", this.ingresoId)
        result.putLong("clienteId", this.clienteId)
        return result
      }
  }

  public companion object {
    public fun actionIngresoListaFragmentToIngresoEditFragment(clienteId: Long, ingresoId: Long
        = -1L): NavDirections = ActionIngresoListaFragmentToIngresoEditFragment(clienteId,
        ingresoId)
  }
}
