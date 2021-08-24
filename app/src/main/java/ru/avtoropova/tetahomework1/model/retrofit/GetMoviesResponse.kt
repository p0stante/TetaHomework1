package ru.avtoropova.tetahomework1.model.retrofit

import com.google.gson.annotations.SerializedName
import ru.avtoropova.tetahomework1.model.room.entities.*

data class GetMoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<Movie>,
    @SerializedName("total_pages") val pages: Int
)

data class GetTagsResponse(
    @SerializedName("genres")
    val tags: List<Tag>
)

data class GetActorsTagsResponse(
    @SerializedName("genres")
    val tags: List<Tag>,
    @SerializedName("credits")
    val cast: Cast
)

data class Cast(
    @SerializedName("cast")
    val actors: List<Actor>
)

data class AgeRestrictionResponse(
    @SerializedName("iso_3166_1")
    val iso: String,
    @SerializedName("release_dates")
    val releaseDates: List<ReleaseDate>
)

data class GetAgeRestrictionResponse(
    @SerializedName("results")
    val response: List<AgeRestrictionResponse>
)