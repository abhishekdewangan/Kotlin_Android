package barqexp.mersattech.firstkotlinproject.ui.main_screen.adapters

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import barqexp.mersattech.firstkotlinproject.R
import barqexp.mersattech.firstkotlinproject.data.Content
import barqexp.mersattech.firstkotlinproject.data.Contents
import barqexp.mersattech.firstkotlinproject.utils.Keys
import barqexp.mersattech.firstkotlinproject.utils.RecyclerItemDecorator
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie_feed.*


class ContentsFeedAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var contents: List<Contents> = listOf()
    open var contentItemClickListener: ContentItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_feed, null, false)
        return HorizontalMovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contents.size
    }


    fun setData(movies: List<Contents>) {
        this.contents = movies
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is HorizontalMovieViewHolder) {
            holder.setData(contents.get(position))
        }
    }

    inner class HorizontalMovieViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        private val adapter = HorizontalMoviesAdapter();
        init {
            recyclerHorizontalContent.layoutManager = LinearLayoutManager(containerView.context, LinearLayoutManager.HORIZONTAL, false )
            recyclerHorizontalContent.addItemDecoration(RecyclerItemDecorator(RecyclerItemDecorator.ORIENTATION_TYPE_HORIZONTAL))
            recyclerHorizontalContent.adapter = adapter
            tvSeeAll.setOnClickListener({contentItemClickListener?.seeAllContentWithType(contents.get(adapterPosition).sectionTitle,
                    contents.get(adapterPosition).sectionType)})
            adapter.contentItemClickListener = contentItemClickListener
        }

        fun setData(contentType: Contents) {
            adapter.setData(contentType.results)
            tvContentType.text = contentType.sectionTitle
        }
    }

    interface ContentItemClickListener {
        fun seeAllContentWithType(sectionTitle: String, sectionType: String)
        fun contentClicked(content: Content)
    }

}