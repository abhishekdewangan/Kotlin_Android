package barqexp.mersattech.firstkotlinproject.ui.contents_screen

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import barqexp.mersattech.firstkotlinproject.R
import barqexp.mersattech.firstkotlinproject.data.Content
import barqexp.mersattech.firstkotlinproject.utils.Keys
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_content_verticle.*


class ContentsAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_LOADING = 1
    private val VIEW_TYPE_CONTENT = 2
    private var contents = listOf<Content>()
    private var isPaginating = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_LOADING -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_loading, null, false)
                val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT)
                view.layoutParams = lp
                ProgressViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_content_verticle, null, false)
                val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT)
                view.layoutParams = lp
                ContentViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (isPaginating) contents.size + 1 else contents.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position == itemCount - 1 && isPaginating) {
            return VIEW_TYPE_LOADING
        }
        return VIEW_TYPE_CONTENT
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ContentViewHolder) {
            holder.bindData(contents.get(position))
        }
    }

    fun setData(contents: List<Content>) {
        this.contents = contents
        notifyDataSetChanged()
    }

    fun addLoader(status: Boolean) {
        isPaginating = status
        notifyDataSetChanged()
    }

    class ContentViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(content: Content) {
            Picasso.get().load(Keys.IMAGE_BASE_URL + content.poster_path).into(imgMoviePoster)
            if (content.title.isEmpty())
                tvMovieTitle.text = content.name
            else
                tvMovieTitle.text = content.title
            tvAvgRating.text = content.voteAverage.toString()
            tvReleaseDate.text = content.releaseDate
        }
    }

    class ProgressViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(content: Content) {

        }
    }

}