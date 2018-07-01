package barqexp.mersattech.firstkotlinproject.data

import com.google.gson.annotations.SerializedName

data class Movie(
        var adult: Boolean = false,
        @SerializedName("backdrop_path")
        var backdropPath: String = "",
        @SerializedName("genre_ids")
        var genreIds: List<Int> = listOf(),
        var id: String = "",
        @SerializedName("original_language")
        var originalLanguage: String = "",
        @SerializedName("original_title")
        var originalTitle: String = "",
        var overview: String = "",
        var popularity: Double? = null,
        var poster_path: String = "",
        @SerializedName("release_date")
        var releaseDate: String? = null,
        var title: String = "",
        var video: Boolean = false,
        @SerializedName("vote_average")
        var voteAverage: Float? = null,
        @SerializedName("vote_count")
        var voteCount: Int = 0)

data class Movies(
        @SerializedName("total_results")
        var totalResults: Int = 0,
        @SerializedName("total_pages")
        var totalPages: Int = 0,
        @SerializedName("results")
        var results: List<Movie>? = null)




