package eu.villacristina.lechugapro.ui.produccion

import android.os.Bundle
import androidx.navigation.NavDirections
import eu.villacristina.lechugapro.R
import kotlin.Int
import kotlin.Long

public class ProduccionDetalleFragmentDirections private constructor() {
  private data class ActionProduccionDetalleFragmentToProduccionEditFragment(
    public val cicloId: Long = -1L,
  ) : NavDirections {
    public override val actionId: Int =
        R.id.action_produccionDetalleFragment_to_produccionEditFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("cicloId", this.cicloId)
        return result
      }
  }

  public companion object {
    public fun actionProduccionDetalleFragmentToProduccionEditFragment(cicloId: Long = -1L):
        NavDirections = ActionProduccionDetalleFragmentToProduccionEditFragment(cicloId)
  }
}
