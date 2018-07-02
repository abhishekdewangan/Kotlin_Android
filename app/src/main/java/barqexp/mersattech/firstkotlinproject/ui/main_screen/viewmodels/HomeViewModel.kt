package barqexp.mersattech.firstkotlinproject.ui.main_screen.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import barqexp.mersattech.firstkotlinproject.data.Movies
import barqexp.mersattech.firstkotlinproject.network.MoviesServices
import barqexp.mersattech.firstkotlinproject.network.RetrofitService
import barqexp.mersattech.firstkotlinproject.utils.Keys
import kotlinx.coroutines.experimental.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val moviesServices: MoviesServices = RetrofitService.create()
    private val contentMap = HashMap<String, MutableLiveData<List<Movies>>>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()

    private suspend fun fetchMovies() {
        val movies = arrayListOf<Movies>()
        try {
            val upcomingMovies: Movies = moviesServices.getMovies(Keys.PARAMS_UPCOMING, Keys.API_KEY, Keys.LANGUAGE_US_EN, 1).await()
            upcomingMovies.type = Keys.MOVIE_TYPE_UPCOMING;
            movies.add(upcomingMovies)

            val nowPlayingMovies: Movies = moviesServices.getMovies(Keys.PARAMS_NOW_PLAYING, Keys.API_KEY, Keys.LANGUAGE_US_EN, 1).await()
            nowPlayingMovies.type = Keys.MOVIE_TYPE_NOW_PLAYING;
            movies.add(nowPlayingMovies)

            val popularMovies: Movies = moviesServices.getMovies(Keys.PARAMS_POPULAR, Keys.API_KEY, Keys.LANGUAGE_US_EN,1).await()
            popularMovies.type = Keys.MOVIE_TYPE_POPULAR;
            movies.add(popularMovies)

            val topRatedMovies: Movies = moviesServices.getMovies(Keys.PARAMS_TOP_RATED, Keys.API_KEY, Keys.LANGUAGE_US_EN, 1).await()
            topRatedMovies.type = Keys.MOVIE_TYPE_TOP_RATED;
            movies.add(topRatedMovies)
            contentMap.get(Keys.CONTENT_TYPE_MOVIES)?.postValue(movies)
            isLoadingLiveData.postValue(false)
        } catch (exception: Exception) {
            Log.e("fetchMovies", "fetch movie exception : ${exception.toString()}")
        }
    }

    private suspend fun fetchTvShows() {
        val tvShows = arrayListOf<Movies>()
        isLoadingLiveData.postValue(true)
        try {
            val popularShows: Movies = moviesServices.getShows(Keys.PARAMS_POPULAR, Keys.API_KEY, Keys.LANGUAGE_US_EN, 1).await()
            popularShows.type = Keys.TV_SHOW_TYPE_POPULAR;
            tvShows.add(popularShows)

            val topRatedShows: Movies = moviesServices.getShows(Keys.PARAMS_TOP_RATED, Keys.API_KEY, Keys.LANGUAGE_US_EN, 1).await()
            topRatedShows.type = Keys.TV_SHOW_TYPE_TOP_RATED;
            tvShows.add(topRatedShows)
            contentMap.get(Keys.CONTENT_TYPE_SHOWS)?.postValue(tvShows)
            isLoadingLiveData.postValue(false)
        } catch (exception: Exception) {
            Log.e("fetchShows", "exception while fetching shows : ${exception.toString()}")
        }
    }

    fun fetchContent(contentType: String) : LiveData<List<Movies>>? {
        if (!contentMap.contains(contentType))
             contentMap.put(contentType, MutableLiveData())
        launch {
            when (contentType) {
                Keys.CONTENT_TYPE_MOVIES -> fetchMovies()
                Keys.CONTENT_TYPE_SHOWS -> fetchTvShows()
            }
        }
        return contentMap.get(contentType)
    }

    fun isContentLoading() : LiveData<Boolean> {
        return isLoadingLiveData
    }

}