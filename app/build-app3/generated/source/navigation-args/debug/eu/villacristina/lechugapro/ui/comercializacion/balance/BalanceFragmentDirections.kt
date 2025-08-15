package eu.villacristina.lechugapro.ui.comercializacion.balance

import android.os.Bundle
import androidx.navigation.NavDirections
import eu.villacristina.lechugapro.R
import kotlin.Int
import kotlin.Long

public class BalanceFragmentDirections private constructor() {
  private data class ActionBalanceFragmentToIngresoEditFragment(
    public val clienteId: Long,
    public val ingresoId: Long = -1L,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_balanceFragment_to_ingresoEditFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("ingresoId", this.ingresoId)
        result.putLong("clienteId", this.clienteId)
        return result
      }
  }

  public companion object {
    public fun actionBalanceFragmentToIngresoEditFragment(clienteId: Long, ingresoId: Long = -1L):
        NavDirections = ActionBalanceFragmentToIngresoEditFragment(clienteId, ingresoId)
  }
}
