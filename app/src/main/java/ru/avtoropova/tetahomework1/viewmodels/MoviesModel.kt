package ru.avtoropova.tetahomework1.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.avtoropova.tetahomework1.model.retrofit.RetrofitMoviesRepository
import ru.avtoropova.tetahomework1.model.room.AppDB
import ru.avtoropova.tetahomework1.model.room.entities.*
import ru.avtoropova.tetahomework1.view.fragments.MoviesListFragment

typealias MyViewState = MoviesListFragment.ViewState

class MoviesModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDB.getAppDB(application)
    private val retrofitRepo = RetrofitMoviesRepository()

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    private val _movieTags = MutableLiveData<List<Tag>>()
    val movieTags: LiveData<List<Tag>>
        get() = _movieTags

    val viewState: LiveData<MyViewState> get() = _viewState
    private val _viewState = MutableLiveData<MyViewState>()

    private val _actors = MutableLiveData<List<Actor>>()
    val actors: LiveData<List<Actor>>
        get() = _actors


    init {
        getMovies()
    }

    private fun fillDB(movies: List<Movie>) {
        db!!.movieDao().addMovies(movies)
    }


    private fun getMovies() {
        viewModelScope.launch {
            try {
                val movies = retrofitRepo.getMovies()
                _movies.postValue(movies)
            } catch (e: Exception) {
                Log.d("Exception", e.toString())
            }
            movies.value?.let { fillDB(it) }
        }
    }

    fun getNextMovies() {
        viewModelScope.launch {
            try {
                val movies = retrofitRepo.getNextMovies()
                _movies.postValue(movies)
            } catch (e: Exception) {
                Log.d("Exception", e.toString())
            }
            movies.value?.let { fillDB(it) }
        }
    }

    fun newMovieDetails(movie: Movie) {
        viewModelScope.launch {
            try {
                val actorsAndTags = retrofitRepo.getActorsAndTags(movie.movieId)
                _actors.postValue(actorsAndTags.cast.actors)
                _movie.postValue(movie)
                _movieTags.postValue(actorsAndTags.tags)
            } catch (e: Exception) {
                Log.d("ExceptionActors", e.toString())
            }
            actors.value?.forEach {
                db!!.movieDao().insertActor(it)
                db.movieDao().addMovieActor(MoviesActorsCrossRef(movie.movieId, it.actorId))
            }
            movieTags.value?.forEach {
                db!!.movieDao().insertTag(it)
                db.movieDao().addMovieTag(MoviesTagsCrossRef(movie.movieId, it.tagId))
            }
        }
    }
}