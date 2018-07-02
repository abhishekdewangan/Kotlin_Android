package barqexp.mersattech.firstkotlinproject.ui.main_screen.activities

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import barqexp.mersattech.firstkotlinproject.R
import barqexp.mersattech.firstkotlinproject.ui.main_screen.adapters.HomePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val pagerAdapter: HomePagerAdapter = HomePagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        setupPagerAdapter()
    }

    fun setupToolbar() {
        toolbar.setTitle("IMDB")
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
    }

    fun setupPagerAdapter() {
        tabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.white),
                ContextCompat.getColor(this, R.color.colorAccent))
        tabLayout.setupWithViewPager(viewPager)
        viewPager.adapter = pagerAdapter
    }
}
