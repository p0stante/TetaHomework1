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
}


