package barqexp.mersattech.firstkotlinproject.ui.main_screen.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import barqexp.mersattech.firstkotlinproject.R
import barqexp.mersattech.firstkotlinproject.data.Movies
import barqexp.mersattech.firstkotlinproject.ui.main_screen.adapters.MoviesFeedAdapter
import barqexp.mersattech.firstkotlinproject.ui.main_screen.viewmodels.HomeViewModel
import barqexp.mersattech.firstkotlinproject.utils.RecyclerItemDecorator
import kotlinx.android.synthetic.main.home_feed_fragment.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private val recyclerAdapter: MoviesFeedAdapter = MoviesFeedAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fetchMovies()
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
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
    }

    private fun fetchMovies() {
        launch(UI) {
            val movies: ArrayList<Movies> = homeViewModel.fetchMovies()
            recyclerAdapter.setData(movies)
        }
    }
}