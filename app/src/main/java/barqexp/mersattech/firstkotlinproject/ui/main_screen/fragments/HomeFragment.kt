package barqexp.mersattech.firstkotlinproject.ui.main_screen.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import barqexp.mersattech.firstkotlinproject.R
import barqexp.mersattech.firstkotlinproject.ui.main_screen.viewmodels.HomeViewModel
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class HomeFragment : Fragment() {
    var homeViewModel: HomeViewModel? = null;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fetchMovies()
        return inflater.inflate(R.layout.home_fragment, null)
    }

    private fun fetchMovies() {
        homeViewModel = activity?.application?.let { HomeViewModel(it) }
        launch(UI) {
            homeViewModel?.fetchMovies()
        }
    }
}