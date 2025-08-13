package eu.villacristina.lechugapro.ui.produccion

import android.os.Bundle
import androidx.navigation.NavDirections
import eu.villacristina.lechugapro.R
import kotlin.Int
import kotlin.Long

public class ProduccionListaFragmentDirections private constructor() {
  private data class ActionProduccionListaFragmentToProduccionEditFragment(
    public val cicloId: Long = -1L,
  ) : NavDirections {
    public override val actionId: Int =
        R.id.action_produccionListaFragment_to_produccionEditFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("cicloId", this.cicloId)
        return result
      }
  }

  private data class ActionProduccionListaFragmentToProduccionDetalleFragment(
    public val cicloId: Long,
  ) : NavDirections {
    public override val actionId: Int =
        R.id.action_produccionListaFragment_to_produccionDetalleFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("cicloId", this.cicloId)
        return result
      }
  }

  public companion object {
    public fun actionProduccionListaFragmentToProduccionEditFragment(cicloId: Long = -1L):
        NavDirections = ActionProduccionListaFragmentToProduccionEditFragment(cicloId)

    public fun actionProduccionListaFragmentToProduccionDetalleFragment(cicloId: Long):
        NavDirections = ActionProduccionListaFragmentToProduccionDetalleFragment(cicloId)
  }
}
