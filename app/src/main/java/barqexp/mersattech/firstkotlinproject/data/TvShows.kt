package barqexp.mersattech.firstkotlinproject.data

import com.google.gson.annotations.SerializedName

data class TVShow(
        var id: String,
        @SerializedName("original_name")
        var originalName: String = "",
        @SerializedName("genre_ids")
        var genreIds: List<Int> = listOf(),
        var name: String = "",
        var popularity: Double? = null,
        @SerializedName("origin_country")
        var originCountry: List<String> = listOf(),
        @SerializedName("vote_count")
        var voteCount: Int = 0,
        @SerializedName("first_air_date")
        var firstAirDate: String = "",
        @SerializedName("backdrop_path")
        var backdropPath: String? = null,
        @SerializedName("original_language")
        var originalLanguage: String? = null,
        @SerializedName("vote_average")
        var avgVote: Float? = null,
        @SerializedName("overview")
        var overview: String? = null,
        @SerializedName("poster_path")
        var posterPath: String? = null
)

data class TvShows(
        @SerializedName("total_results")
        var totalResults: Int = 0,
        @SerializedName("total_pages")
        var totalPages: Int = 0,
        @SerializedName("results")
        var results: List<TVShow>? = null)
