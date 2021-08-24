package ru.avtoropova.tetahomework1.model.room.entities

import androidx.room.*
import com.google.gson.annotations.SerializedName


@Entity(tableName = "Movies")
data class Movie(
    @SerializedName("title")
    val title: String?,
    @SerializedName("overview")
    val description: String?,
    @SerializedName("vote_average")
    var rateScore: Float,
    @SerializedName("certification")
    var ageRestriction: String,
    @SerializedName("poster_path")
    var imageUrl: String?,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("id")
    @PrimaryKey val movieId: Long
)

@Entity(primaryKeys = ["movieId", "actorId"])
data class MoviesActorsCrossRef(
    val movieId: Long,
    val actorId: Long
)

data class MoviesWithActors(
    @Embedded val movie: Movie,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "actorId",
        entity = Actor::class,
        associateBy = Junction(
            MoviesActorsCrossRef::class,
            parentColumn = "movieId",
            entityColumn = "actorId"
        )
    )
    val actors: List<Actor>
)

data class ReleaseDate(
    @SerializedName("certification")
    val certication: String
)

@Entity(primaryKeys = ["movieId", "tagId"])
data class MoviesTagsCrossRef(
    val movieId: Long,
    val tagId: Long
)

data class MovieWithTags(
    @Embedded val movie: Movie,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "tagId",
        entity = Tag::class,
        associateBy = Junction(
            MoviesTagsCrossRef::class,
            parentColumn = "movieId",
            entityColumn = "tagId"
        )
    )
    val tags: List<Tag>
)

data class TagWithMovies(
    @Embedded val tag: Tag,
    @Relation(
        parentColumn = "tagId",
        entityColumn = "movieId",
        entity = Movie::class,
        associateBy = Junction(
            MoviesTagsCrossRef::class,
            parentColumn = "tagId",
            entityColumn = "movieId"
        )
    )
    val movies: List<Movie>
)