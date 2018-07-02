package barqexp.mersattech.firstkotlinproject.ui.contents_screen

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import barqexp.mersattech.firstkotlinproject.R
import barqexp.mersattech.firstkotlinproject.data.Content
import kotlinx.android.extensions.LayoutContainer

class ContentsAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var contents = listOf<Content>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_content_verticle, null, false)
        return ContentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    fun setData(contents: List<Content>) {
        this.contents = contents
        notifyDataSetChanged()
    }

    class ContentViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(content: Content) {

        }
    }
}