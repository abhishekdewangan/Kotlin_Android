package barqexp.mersattech.firstkotlinproject.ui.main_screen.adapters

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import barqexp.mersattech.firstkotlinproject.ui.main_screen.fragments.HomeFragment
import barqexp.mersattech.firstkotlinproject.utils.Keys

class HomePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null;
        val bundle = Bundle();
        when (position) {
            0 -> {
                fragment = HomeFragment()
                bundle.putString(Keys.BUNDLE_CONTENT_TYPE, Keys.CONTENT_TYPE_MOVIES)
            }
            1 -> {
                fragment = HomeFragment()
                bundle.putString(Keys.BUNDLE_CONTENT_TYPE, Keys.CONTENT_TYPE_SHOWS)
            }
            else -> fragment = HomeFragment()
        }
        fragment.arguments = bundle
        return fragment;
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Contents"
            1 -> "TV-Series"
            else -> "Contents"
        }
    }

}