package barqexp.mersattech.firstkotlinproject.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesServices {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") key: String, @Query("language") lang: String,
                         @Query("page") page: Int)

    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("api_key") key: String, @Query("language") lang: String,
                            @Query("page") page: Int)

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") key: String, @Query("language") lang: String,
                          @Query("page") page: Int)

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("api_key") key: String, @Query("language") lang: String,
                          @Query("page") page: Int)

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: String, @Query("api_key") key: String,
                        @Query("language") lang: String, @Query("page") page: Int)

    @GET("tv/popular")
    fun getPopularTVShows(@Query("api_key") key: String, @Query("language") lang: String,
                          @Query("page") page: Int)


    @GET("tv/top_rated")
    fun getTopRatedTVShows(@Query("api_key") key: String, @Query("language") lang: String,
                           @Query("page") page: Int)

    @GET("tv/{show_id}")
    fun getTVShowDetails(@Path("show_id") showId: String, @Query("api_key") key: String,
                         @Query("language") lang: String, @Query("page") page: Int)


}