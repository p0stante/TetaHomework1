package ru.avtoropova.tetahomework1.model.room.entities

import androidx.room.*


@Entity(tableName = "Movies")
data class Movie(
    val title: String?,
    val description: String?,
    val rateScore: Int,
    val ageRestriction: Int,
    val imageUrl: String?,
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