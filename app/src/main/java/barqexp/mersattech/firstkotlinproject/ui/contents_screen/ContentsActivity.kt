package barqexp.mersattech.firstkotlinproject.ui.contents_screen

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import barqexp.mersattech.firstkotlinproject.R
import barqexp.mersattech.firstkotlinproject.utils.Keys
import kotlinx.android.synthetic.main.layout_content_list.*

class ContentsActivity : AppCompatActivity() {
    lateinit var contentType: String
    lateinit var adapter: ContentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_content_list)
        contentType = intent.extras.getString(Keys.BUNDLE_CONTENT_TYPE)
        initialSetup()
        setAdapter()

    }

    private fun initialSetup() {
        val title: String = when (contentType) {
            Keys.MOVIE_TYPE_TOP_RATED -> getString(R.string.top_rated_movies)
            Keys.MOVIE_TYPE_POPULAR -> getString(R.string.popular_movies)
            Keys.MOVIE_TYPE_NOW_PLAYING -> getString(R.string.in_theaters)
            Keys.MOVIE_TYPE_UPCOMING -> getString(R.string.coming_soon)
            Keys.TV_SHOW_TYPE_POPULAR -> getString(R.string.popular_shows)
            Keys.TV_SHOW_TYPE_TOP_RATED -> getString(R.string.top_rated_shows)
            else -> ""
        }

        toolbar.run {
            setTitle(title)
            setTitleTextColor(ContextCompat.getColor(context, R.color.white))
            setNavigationIcon(R.drawable.ic_back)
            setNavigationOnClickListener({ onBackPressed() })
        }

    }

    private fun setAdapter() {
        adapter = ContentsAdapter()
        recyclerContents.layoutManager = LinearLayoutManager(this)
        recyclerContents.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerContents.adapter = adapter
    }
}