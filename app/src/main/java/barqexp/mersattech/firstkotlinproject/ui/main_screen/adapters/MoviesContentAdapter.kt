package barqexp.mersattech.firstkotlinproject.ui.main_screen.adapters

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import barqexp.mersattech.firstkotlinproject.R
import barqexp.mersattech.firstkotlinproject.data.Movies
import barqexp.mersattech.firstkotlinproject.utils.RecyclerItemDecorator
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_content_type.*


class MoviesContentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    public var movies: List<Movies> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_content_type, null, false)
        return HorizontalMovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    public fun setData(movies: List<Movies>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is HorizontalMovieViewHolder) {
            holder.setData(movies.get(position))
        }
    }

    class HorizontalMovieViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        private val adapter = MoviesFeedRecyclerAdapter();
        init {
            recyclerHorizontalContent.layoutManager = LinearLayoutManager(containerView.context, LinearLayoutManager.HORIZONTAL, false )
            recyclerHorizontalContent.addItemDecoration(RecyclerItemDecorator(RecyclerItemDecorator.ORIENTATION_TYPE_HORIZONTAL))
            recyclerHorizontalContent.adapter = adapter
        }

        fun setData(movieList: Movies) {
           adapter.setData(movieList.results)
        }
    }


}