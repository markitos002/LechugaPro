package eu.villacristina.lechugapro.ui.comercializacion.ingreso

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Long
import kotlin.jvm.JvmStatic

public data class IngresoEditFragmentArgs(
  public val clienteId: Long,
  public val ingresoId: Long = -1L,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putLong("ingresoId", this.ingresoId)
    result.putLong("clienteId", this.clienteId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("ingresoId", this.ingresoId)
    result.set("clienteId", this.clienteId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): IngresoEditFragmentArgs {
      bundle.setClassLoader(IngresoEditFragmentArgs::class.java.classLoader)
      val __ingresoId : Long
      if (bundle.containsKey("ingresoId")) {
        __ingresoId = bundle.getLong("ingresoId")
      } else {
        __ingresoId = -1L
      }
      val __clienteId : Long
      if (bundle.containsKey("clienteId")) {
        __clienteId = bundle.getLong("clienteId")
      } else {
        throw IllegalArgumentException("Required argument \"clienteId\" is missing and does not have an android:defaultValue")
      }
      return IngresoEditFragmentArgs(__clienteId, __ingresoId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): IngresoEditFragmentArgs {
      val __ingresoId : Long?
      if (savedStateHandle.contains("ingresoId")) {
        __ingresoId = savedStateHandle["ingresoId"]
        if (__ingresoId == null) {
          throw IllegalArgumentException("Argument \"ingresoId\" of type long does not support null values")
        }
      } else {
        __ingresoId = -1L
      }
      val __clienteId : Long?
      if (savedStateHandle.contains("clienteId")) {
        __clienteId = savedStateHandle["clienteId"]
        if (__clienteId == null) {
          throw IllegalArgumentException("Argument \"clienteId\" of type long does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"clienteId\" is missing and does not have an android:defaultValue")
      }
      return IngresoEditFragmentArgs(__clienteId, __ingresoId)
    }
  }
}
