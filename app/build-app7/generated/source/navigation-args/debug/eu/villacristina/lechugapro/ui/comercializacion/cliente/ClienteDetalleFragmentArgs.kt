package eu.villacristina.lechugapro.ui.comercializacion.cliente

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Long
import kotlin.jvm.JvmStatic

public data class ClienteDetalleFragmentArgs(
  public val clienteId: Long,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putLong("clienteId", this.clienteId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("clienteId", this.clienteId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): ClienteDetalleFragmentArgs {
      bundle.setClassLoader(ClienteDetalleFragmentArgs::class.java.classLoader)
      val __clienteId : Long
      if (bundle.containsKey("clienteId")) {
        __clienteId = bundle.getLong("clienteId")
      } else {
        throw IllegalArgumentException("Required argument \"clienteId\" is missing and does not have an android:defaultValue")
      }
      return ClienteDetalleFragmentArgs(__clienteId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle):
        ClienteDetalleFragmentArgs {
      val __clienteId : Long?
      if (savedStateHandle.contains("clienteId")) {
        __clienteId = savedStateHandle["clienteId"]
        if (__clienteId == null) {
          throw IllegalArgumentException("Argument \"clienteId\" of type long does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"clienteId\" is missing and does not have an android:defaultValue")
      }
      return ClienteDetalleFragmentArgs(__clienteId)
    }
  }
}
