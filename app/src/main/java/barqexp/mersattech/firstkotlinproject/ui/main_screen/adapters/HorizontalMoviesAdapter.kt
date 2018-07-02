package barqexp.mersattech.firstkotlinproject.ui.main_screen.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import barqexp.mersattech.firstkotlinproject.R
import barqexp.mersattech.firstkotlinproject.data.Content
import barqexp.mersattech.firstkotlinproject.utils.Keys
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_horizontal_movie.*

class HorizontalMoviesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var contents: List<Content> = listOf();
    var contentItemClickListener: ContentsFeedAdapter.ContentItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal_movie, null, false)
        return FeedContentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contents.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FeedContentViewHolder) {
            holder.bindData(contents.get(position))
        }
    }

    fun setData(contents: List<Content>) {
        this.contents = contents
        notifyDataSetChanged()
    }

    inner class FeedContentViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        init {
            containerView?.setOnClickListener({contentItemClickListener?.contentClicked(contents.get(adapterPosition))})
        }
        fun bindData(content: Content) {
            Picasso.get().load(Keys.IMAGE_BASE_URL + content.poster_path).into(imgMoviePoster)
            tvMovieTitle.setText("${content.voteAverage}")
        }
    }
}