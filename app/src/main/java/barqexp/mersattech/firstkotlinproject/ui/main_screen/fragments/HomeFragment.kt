package barqexp.mersattech.firstkotlinproject.ui.main_screen.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import barqexp.mersattech.firstkotlinproject.R
import barqexp.mersattech.firstkotlinproject.data.Content
import barqexp.mersattech.firstkotlinproject.ui.contents_screen.ContentsActivity
import barqexp.mersattech.firstkotlinproject.ui.main_screen.adapters.ContentsFeedAdapter
import barqexp.mersattech.firstkotlinproject.ui.main_screen.viewmodels.HomeViewModel
import barqexp.mersattech.firstkotlinproject.utils.Keys
import barqexp.mersattech.firstkotlinproject.utils.RecyclerItemDecorator
import kotlinx.android.synthetic.main.home_feed_fragment.*

class HomeFragment : Fragment(), ContentsFeedAdapter.ContentItemClickListener {
    private lateinit var homeViewModel: HomeViewModel
    private val recyclerAdapter: ContentsFeedAdapter = ContentsFeedAdapter()
    private var contentType: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        contentType = arguments?.getString(Keys.BUNDLE_CONTENT_TYPE, "")!!
        subscribeToContents()
        subscribeToContentLoading()
        Log.e("contentType", " content Type is ::: $contentType")
        return inflater.inflate(R.layout.home_feed_fragment, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        moviesRecyclerView.layoutManager = LinearLayoutManager(context)
        moviesRecyclerView.addItemDecoration(RecyclerItemDecorator(RecyclerItemDecorator.ORIENTATION_TYPE_VERTICAL))
        moviesRecyclerView.adapter = recyclerAdapter
        recyclerAdapter.contentItemClickListener = this
    }

    private fun subscribeToContents() {
        homeViewModel.fetchContent(contentType)?.observe(this, Observer {
            recyclerAdapter.setData(it!!)
        })
    }

    private fun subscribeToContentLoading() {
        homeViewModel.isContentLoading().observe(this, Observer {
            if (it!!) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })
    }

    override fun seeAllContentWithType(sectionTitle: String, sectionType: String) {
        val intent = Intent(context, ContentsActivity::class.java)
        val bundle = Bundle()
        bundle.putString(Keys.BUNDLE_CONTENT_TYPE, contentType)
        bundle.putString(Keys.BUNDLE_CONTENT_FILTER_TYPE, sectionType)
        bundle.putString(Keys.BUNDLE_TITLE, sectionTitle)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun contentClicked(content: Content) {
    }

}