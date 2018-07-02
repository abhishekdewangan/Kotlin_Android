package barqexp.mersattech.firstkotlinproject.ui.main_screen.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import barqexp.mersattech.firstkotlinproject.R
import barqexp.mersattech.firstkotlinproject.data.Movie
import kotlinx.android.extensions.LayoutContainer

class MoviesFeedRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var contentList: List<Movie> = listOf();

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal_movie, null, false)
        return FeedContentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FeedContentViewHolder) {

        }
    }

    public fun setData(movies: List<Movie>) {
        contentList = movies
        notifyDataSetChanged()
    }

    class FeedContentViewHolder constructor(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        init {

        }
    }
}