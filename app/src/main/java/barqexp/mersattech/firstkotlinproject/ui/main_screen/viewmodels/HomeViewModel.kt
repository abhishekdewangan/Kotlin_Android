package barqexp.mersattech.firstkotlinproject.ui.main_screen.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import barqexp.mersattech.firstkotlinproject.KotlinApplication
import barqexp.mersattech.firstkotlinproject.R
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
        val application = getApplication<KotlinApplication>()
        val movies = arrayListOf<Contents>()
        try {
            val upcomingContents: Contents = moviesServices.getMovies(Keys.PARAMS_UPCOMING, Keys.API_KEY, Keys.LANGUAGE_US_EN, 1).await()
            upcomingContents.sectionTitle = application.getString(R.string.coming_soon)
            upcomingContents.sectionType = Keys.PARAMS_UPCOMING
            movies.add(upcomingContents)

            val nowPlayingContents: Contents = moviesServices.getMovies(Keys.PARAMS_NOW_PLAYING, Keys.API_KEY, Keys.LANGUAGE_US_EN, 1).await()
            nowPlayingContents.sectionTitle = application.getString(R.string.in_theaters)
            nowPlayingContents.sectionType = Keys.PARAMS_NOW_PLAYING
            movies.add(nowPlayingContents)

            val popularContents: Contents = moviesServices.getMovies(Keys.PARAMS_POPULAR, Keys.API_KEY, Keys.LANGUAGE_US_EN, 1).await()
            popularContents.sectionTitle = application.getString(R.string.popular_movies)
            popularContents.sectionType = Keys.PARAMS_POPULAR
            movies.add(popularContents)

            val topRatedContents: Contents = moviesServices.getMovies(Keys.PARAMS_TOP_RATED, Keys.API_KEY, Keys.LANGUAGE_US_EN, 1).await()
            topRatedContents.sectionTitle = application.getString(R.string.top_rated_movies)
            topRatedContents.sectionType = Keys.PARAMS_TOP_RATED

            movies.add(topRatedContents)
            contentMap.get(Keys.CONTENT_TYPE_MOVIES)?.postValue(movies)
            isLoadingLiveData.postValue(false)
        } catch (exception: Exception) {
            Log.e("fetchMovies", "fetch movie exception : ${exception.toString()}")
        }
    }

    private suspend fun fetchTvShows() {
        val tvShows = arrayListOf<Contents>()
        val application = getApplication<KotlinApplication>()
        isLoadingLiveData.postValue(true)
        try {
            val popularShows: Contents = moviesServices.getShows(Keys.PARAMS_POPULAR, Keys.API_KEY, Keys.LANGUAGE_US_EN, 1).await()
            popularShows.sectionTitle = application.getString(R.string.popular_shows)
            popularShows.sectionType = Keys.PARAMS_POPULAR
            tvShows.add(popularShows)

            val topRatedShows: Contents = moviesServices.getShows(Keys.PARAMS_TOP_RATED, Keys.API_KEY, Keys.LANGUAGE_US_EN, 1).await()
            topRatedShows.sectionTitle = application.getString(R.string.top_rated_shows)
            topRatedShows.sectionType = Keys.PARAMS_TOP_RATED

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