package barqexp.mersattech.firstkotlinproject.ui.main_screen.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import barqexp.mersattech.firstkotlinproject.R
import barqexp.mersattech.firstkotlinproject.data.Movie
import barqexp.mersattech.firstkotlinproject.utils.Keys
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_horizontal_movie.*

class HorizontalMoviesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var movies: List<Movie> = listOf();

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal_movie, null, false)
        return FeedContentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FeedContentViewHolder) {
            holder.bindData(movies.get(position))
        }
    }

    public fun setData(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    class FeedContentViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(movie: Movie) {
            Picasso.get().load(Keys.IMAGE_BASE_URL+movie.poster_path).into(imgMoviePoster)
            tvMovieTitle.setText("${movie.voteAverage}")
        }
    }
}