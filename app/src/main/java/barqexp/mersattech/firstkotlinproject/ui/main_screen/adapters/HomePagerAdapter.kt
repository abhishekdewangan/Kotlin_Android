package barqexp.mersattech.firstkotlinproject.ui.main_screen.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import barqexp.mersattech.firstkotlinproject.ui.main_screen.fragments.HomeFragment

class HomePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null;
        when (position) {
            0 -> fragment = HomeFragment()
            1 -> fragment = HomeFragment()
            else -> fragment = HomeFragment()
        }
        return fragment;
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Movies"
            1 -> "TV-Series"
            else -> "Movies"
        }
    }

}