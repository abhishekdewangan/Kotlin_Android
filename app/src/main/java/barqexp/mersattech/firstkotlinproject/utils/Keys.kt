package barqexp.mersattech.firstkotlinproject.utils

class Keys {
    companion object {
        const val API_KEY = "b2dd5a70c5e8553d0399398a2e9bc050"

        // IMAGE BASE URL
        const val IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185/"

        /* LANGUAGE KEYS */
        const val LANGUAGE_US_EN = "en-US";

        /* CONTENT TYPES*/
        const val CONTENT_TYPE_MOVIES = "movie"
        const val CONTENT_TYPE_SHOWS = "tv"


        /* BUNDLE KEYS */
        const val BUNDLE_CONTENT_TYPE= "CONTENT_TYPE"
        const val BUNDLE_CONTENT_FILTER_TYPE= "CONTENT_FILTER_TYPE"
        const val BUNDLE_TITLE = "TITLE"

        /* Query Params Values*/
        const val PARAMS_POPULAR = "popular"
        const val PARAMS_NOW_PLAYING = "now_playing"
        const val PARAMS_TOP_RATED = "top_rated"
        const val PARAMS_UPCOMING = "upcoming"

        /* LOADING STATUS*/
        const val LOADING_FRESH = 1
        const val LOADING_NEXT = 2
        const val STOP_LOADING = 3
        const val STOP_PAGINATION = 4

    }
}