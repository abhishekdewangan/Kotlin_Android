package barqexp.mersattech.firstkotlinproject.network

import android.graphics.Movie
import barqexp.mersattech.firstkotlinproject.data.Contents
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesServices {

    @GET("movie/{movie_type}")
    fun getMovies(@Path("movie_type") movieType: String, @Query("api_key")
    key: String, @Query("language") lang: String, @Query("page") page: Int): Deferred<Contents>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: String, @Query("api_key") key: String,
                        @Query("language") lang: String, @Query("page") page: Int): Deferred<Movie>

    //{popular   top_rated}
    @GET("tv/{tv_show_type}")
    fun getShows(@Path("tv_show_type") showType: String, @Query("api_key") key: String, @Query("language") lang: String,
                 @Query("page") page: Int): Deferred<Contents>

    @GET("tv/{show_id}")
    fun getTVShowDetails(@Path("show_id") showId: String, @Query("api_key") key: String,
                         @Query("language") lang: String, @Query("page") page: Int)

    @GET("{content_type}/{content_filter_type}")
    fun getContents(@Path("content_type") contentType: String, @Path("content_filter_type") contentFilterType: String, @Query("api_key")
    key: String, @Query("language") lang: String, @Query("page") page: Int): Deferred<Contents>


}