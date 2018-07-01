package barqexp.mersattech.firstkotlinproject.ui.main_screen.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import barqexp.mersattech.firstkotlinproject.data.Movies
import barqexp.mersattech.firstkotlinproject.network.MoviesServices
import barqexp.mersattech.firstkotlinproject.network.RetrofitService
import barqexp.mersattech.firstkotlinproject.utils.Keys

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val moviesServices: MoviesServices = RetrofitService.create()

    suspend fun fetchMovies(): ArrayList<Movies> {
        val movies = arrayListOf<Movies>()
        try {
            val upcomingMovies: Movies = moviesServices.getMovies(Keys.MOVIE_TYPE_UPCOMING, Keys.API_KEY, Keys.LANGUAGE_US_EN).await()
            upcomingMovies.type = Keys.MOVIE_TYPE_UPCOMING;
            movies.add(upcomingMovies)

            val nowPlayingMovies: Movies = moviesServices.getMovies(Keys.MOVIE_TYPE_NOW_PLAYING, Keys.API_KEY, Keys.LANGUAGE_US_EN).await()
            nowPlayingMovies.type = Keys.MOVIE_TYPE_NOW_PLAYING;
            movies.add(nowPlayingMovies)

            val popularMovies: Movies = moviesServices.getMovies(Keys.MOVIE_TYPE_POPULAR, Keys.API_KEY, Keys.LANGUAGE_US_EN).await()
            popularMovies.type = Keys.MOVIE_TYPE_POPULAR;
            movies.add(popularMovies)

            val topRatedMovies: Movies = moviesServices.getMovies(Keys.MOVIE_TYPE_TOP_RATED, Keys.API_KEY, Keys.LANGUAGE_US_EN).await()
            topRatedMovies.type = Keys.MOVIE_TYPE_TOP_RATED;
            movies.add(topRatedMovies)
        } catch (exception: Exception) {
            Log.e("fetchMovies", "fetch movie exception : ${exception.toString()}")
        }
        return movies
    }


}