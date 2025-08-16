package eu.villacristina.lechugapro.ui.produccion

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Long
import kotlin.jvm.JvmStatic

public data class ProduccionEditFragmentArgs(
  public val cicloId: Long = -1L,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putLong("cicloId", this.cicloId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("cicloId", this.cicloId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): ProduccionEditFragmentArgs {
      bundle.setClassLoader(ProduccionEditFragmentArgs::class.java.classLoader)
      val __cicloId : Long
      if (bundle.containsKey("cicloId")) {
        __cicloId = bundle.getLong("cicloId")
      } else {
        __cicloId = -1L
      }
      return ProduccionEditFragmentArgs(__cicloId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle):
        ProduccionEditFragmentArgs {
      val __cicloId : Long?
      if (savedStateHandle.contains("cicloId")) {
        __cicloId = savedStateHandle["cicloId"]
        if (__cicloId == null) {
          throw IllegalArgumentException("Argument \"cicloId\" of type long does not support null values")
        }
      } else {
        __cicloId = -1L
      }
      return ProduccionEditFragmentArgs(__cicloId)
    }
  }
}
