package ru.avtoropova.tetahomework1.model.room.dao

import androidx.room.*
import ru.avtoropova.tetahomework1.model.room.entities.*

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movie: Movie): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovies(movies: List<Movie>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovieActor(movieActor: MoviesActorsCrossRef): Long

    @Update
    fun updateMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

    @Transaction
    @Query("SELECT * FROM Movies")
    fun getMovies(): List<Movie>

    @Transaction
    @Query("SELECT * FROM Movies")
    fun getMoviesWithActors(): List<MoviesWithActors>

    @Transaction
    @Query("SELECT * FROM Movies WHERE movieId=:id")
    fun getMovieWithActorsById(id: Long): MoviesWithActors

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertActor(actor: Actor)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addActors(actors: List<Actor>)

    @Update
    fun updateActor(actor: Actor)

    @Delete
    fun deleteActor(actor: Actor)

    @Transaction
    @Query("SELECT * FROM Actors")
    fun getActors(): List<Actor>

    @Transaction
    @Query("SELECT * FROM MoviesActorsCrossRef")
    fun getMovieActorsRefs(): List<MoviesActorsCrossRef>

    @Transaction
    @Query("SELECT * FROM Movies WHERE movieId=:id")
    fun getMovieWithTagsById(id: Long): MovieWithTags

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTag(tag: Tag)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovieTag(movieTag: MoviesTagsCrossRef): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTags(tags: List<Tag>)

    @Update
    fun updateTag(tag: Tag)

    @Delete
    fun deleteActor(tag: Tag)

    @Transaction
    @Query("SELECT * FROM Tags")
    fun getTags(): List<Tag>

    @Transaction
    @Query("SELECT * FROM MoviesTagsCrossRef")
    fun getMovieTagsRefs(): List<MoviesTagsCrossRef>

}


