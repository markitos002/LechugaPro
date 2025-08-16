package eu.villacristina.lechugapro.ui.comercializacion.cliente

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Long
import kotlin.jvm.JvmStatic

public data class ClienteEditFragmentArgs(
  public val clienteId: Long = -1L,
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
    public fun fromBundle(bundle: Bundle): ClienteEditFragmentArgs {
      bundle.setClassLoader(ClienteEditFragmentArgs::class.java.classLoader)
      val __clienteId : Long
      if (bundle.containsKey("clienteId")) {
        __clienteId = bundle.getLong("clienteId")
      } else {
        __clienteId = -1L
      }
      return ClienteEditFragmentArgs(__clienteId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): ClienteEditFragmentArgs {
      val __clienteId : Long?
      if (savedStateHandle.contains("clienteId")) {
        __clienteId = savedStateHandle["clienteId"]
        if (__clienteId == null) {
          throw IllegalArgumentException("Argument \"clienteId\" of type long does not support null values")
        }
      } else {
        __clienteId = -1L
      }
      return ClienteEditFragmentArgs(__clienteId)
    }
  }
}
