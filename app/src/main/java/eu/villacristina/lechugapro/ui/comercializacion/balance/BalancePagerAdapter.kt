package eu.villacristina.lechugapro.ui.comercializacion.balance

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class BalancePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> IngresosTabFragment()
        1 -> GastosTabFragment()
        2 -> GananciaTabFragment()
        else -> throw IllegalArgumentException("Invalid tab position")
    }
}
