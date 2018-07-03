package barqexp.mersattech.firstkotlinproject.utils

class Keys {
    companion object {
        const val API_KEY = "b2dd5a70c5e8553d0399398a2e9bc050"

        // IMAGE BASE URL
        const val IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185/"

        /* MOVIES LIST TYPE */
        const val MOVIE_TYPE_POPULAR = "popular_movies"
        const val MOVIE_TYPE_NOW_PLAYING = "now_playing_movies"
        const val MOVIE_TYPE_TOP_RATED = "top_rated_movies"
        const val MOVIE_TYPE_UPCOMING = "upcoming_movies"


        /* TV-SHOWS LIST TYPE*/
        const  val TV_SHOW_TYPE_POPULAR = "popular_shows"
        const val TV_SHOW_TYPE_TOP_RATED = "top_rated_shows"

        /* LANGUAGE KEYS */
        const val LANGUAGE_US_EN = "en-US";

        /* CONTENT TYPES*/
        const val CONTENT_TYPE_MOVIES = "contents"
        const val CONTENT_TYPE_SHOWS = "shows"


        /* BUNDLE KEYS */
        const val BUNDLE_CONTENT_TYPE= "CONTENT_TYPE"
        const val BUNDLE_CONTENT_FILTER_TYPE= "CONTENT_FILTER_TYPE"

        /* Query Params Values*/
        const val PARAMS_POPULAR = "popular"
        const val PARAMS_NOW_PLAYING = "now_playing"
        const val PARAMS_TOP_RATED = "top_rated"
        const val PARAMS_UPCOMING = "upcoming"

        /* CONTENT TYPES*/
        const val CONTENT_TYPE_MOVIE = "movie"
        const val CONTENT_TYPE_TV = "tv"

        /* LOADING STATUS*/
        const val LOADING_FRESH = 1
        const val LOADING_NEXT = 2
        const val STOP_LOADING = 3
        const val STOP_PAGINATION = 4

    }
}