package barqexp.mersattech.firstkotlinproject.ui.main_screen.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import barqexp.mersattech.firstkotlinproject.data.Contents
import barqexp.mersattech.firstkotlinproject.network.MoviesServices
import barqexp.mersattech.firstkotlinproject.network.RetrofitService
import barqexp.mersattech.firstkotlinproject.utils.Keys
import kotlinx.coroutines.experimental.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val moviesServices: MoviesServices = RetrofitService.create()
    private val contentMap = HashMap<String, MutableLiveData<List<Contents>>>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()

    private suspend fun fetchMovies() {
        val movies = arrayListOf<Contents>()
        try {
            val upcomingContents: Contents = moviesServices.getMovies(Keys.PARAMS_UPCOMING, Keys.API_KEY, Keys.LANGUAGE_US_EN, 1).await()
            upcomingContents.type = Keys.MOVIE_TYPE_UPCOMING;
            movies.add(upcomingContents)

            val nowPlayingContents: Contents = moviesServices.getMovies(Keys.PARAMS_NOW_PLAYING, Keys.API_KEY, Keys.LANGUAGE_US_EN, 1).await()
            nowPlayingContents.type = Keys.MOVIE_TYPE_NOW_PLAYING;
            movies.add(nowPlayingContents)

            val popularContents: Contents = moviesServices.getMovies(Keys.PARAMS_POPULAR, Keys.API_KEY, Keys.LANGUAGE_US_EN,1).await()
            popularContents.type = Keys.MOVIE_TYPE_POPULAR;
            movies.add(popularContents)

            val topRatedContents: Contents = moviesServices.getMovies(Keys.PARAMS_TOP_RATED, Keys.API_KEY, Keys.LANGUAGE_US_EN, 1).await()
            topRatedContents.type = Keys.MOVIE_TYPE_TOP_RATED;
            movies.add(topRatedContents)
            contentMap.get(Keys.CONTENT_TYPE_MOVIES)?.postValue(movies)
            isLoadingLiveData.postValue(false)
        } catch (exception: Exception) {
            Log.e("fetchMovies", "fetch movie exception : ${exception.toString()}")
        }
    }

    private suspend fun fetchTvShows() {
        val tvShows = arrayListOf<Contents>()
        isLoadingLiveData.postValue(true)
        try {
            val popularShows: Contents = moviesServices.getShows(Keys.PARAMS_POPULAR, Keys.API_KEY, Keys.LANGUAGE_US_EN, 1).await()
            popularShows.type = Keys.TV_SHOW_TYPE_POPULAR;
            tvShows.add(popularShows)

            val topRatedShows: Contents = moviesServices.getShows(Keys.PARAMS_TOP_RATED, Keys.API_KEY, Keys.LANGUAGE_US_EN, 1).await()
            topRatedShows.type = Keys.TV_SHOW_TYPE_TOP_RATED;
            tvShows.add(topRatedShows)
            contentMap.get(Keys.CONTENT_TYPE_SHOWS)?.postValue(tvShows)
            isLoadingLiveData.postValue(false)
        } catch (exception: Exception) {
            Log.e("fetchShows", "exception while fetching shows : ${exception.toString()}")
        }
    }

    fun fetchContent(contentType: String) : LiveData<List<Contents>>? {
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