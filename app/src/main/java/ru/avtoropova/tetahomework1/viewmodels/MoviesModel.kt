package ru.avtoropova.tetahomework1.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.avtoropova.tetahomework1.model.features.movies.MoviesDataSourceImpl
import ru.avtoropova.tetahomework1.model.room.AppDB
import ru.avtoropova.tetahomework1.model.room.entities.Actor
import ru.avtoropova.tetahomework1.model.room.entities.Movie
import ru.avtoropova.tetahomework1.model.room.entities.MoviesActorsCrossRef
import ru.avtoropova.tetahomework1.model.room.entities.MoviesWithActors
import ru.avtoropova.tetahomework1.utils.MOVIES_POPULATED
import ru.avtoropova.tetahomework1.utils.SHARED_PREFERENCES
import ru.avtoropova.tetahomework1.view.fragments.MoviesListFragment

typealias MyViewState = MoviesListFragment.ViewState

class MoviesModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDB.getAppDB(application)

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private val _movie = MutableLiveData<MoviesWithActors>()
    val movie: LiveData<MoviesWithActors>
        get() = _movie

    val viewState: LiveData<MyViewState> get() = _viewState
    private val _viewState = MutableLiveData<MyViewState>()

    private val _actors = MutableLiveData<List<Actor>>()
    val actors: LiveData<List<Actor>>
        get() = _actors


    init {
        initDB()
        getMovies()
    }

    private fun initDB() {
        viewModelScope.launch {
            val sharedPrefs = getApplication<Application>().getSharedPreferences(
                SHARED_PREFERENCES,
                Context.MODE_PRIVATE
            )
            if (!sharedPrefs.getBoolean(MOVIES_POPULATED, false)) {
                populateMovies()
                sharedPrefs.edit().putBoolean(MOVIES_POPULATED, true).apply()
            }
            val actors = db!!.movieDao().getActors()
            _actors.postValue(actors)
        }
    }


    fun getMoviesShuffle() {
        viewModelScope.launch {
            val movies = db!!.movieDao().getMovies().shuffled()
            _movies.postValue(movies)
        }
    }

    private fun getMovies() {
        viewModelScope.launch {
            val movies = db!!.movieDao().getMovies()
            _movies.postValue(movies)
        }
    }

    private fun populateMovies() {
        val repo = MoviesDataSourceImpl()
        val movies = repo.getMovies()
        var imovie = 1L
        var iactor = 1L
        for (movieDto in movies) {
            val movie = Movie(
                movieDto.title,
                movieDto.description,
                movieDto.rateScore,
                movieDto.ageRestriction,
                movieDto.imageUrl,
                imovie++
            )
            db!!.movieDao().addMovie(movie)
            movieDto.actors?.forEach { actorDto ->
                val actor = Actor(actorDto.name, actorDto.imageUrl, iactor++)
                db.movieDao().insertActor(actor)
                db.movieDao().addMovieActor(MoviesActorsCrossRef(movie.movieId, actor.actorId))
            }
        }

    }

    fun newMovieDetails(id: Long) {
        val movieWithActors = db!!.movieDao().getMovieWithActorsById(id)

        _movie.postValue(movieWithActors)
    }
}