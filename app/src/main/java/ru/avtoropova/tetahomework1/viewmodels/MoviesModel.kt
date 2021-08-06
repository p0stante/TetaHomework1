package ru.avtoropova.tetahomework1.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.avtoropova.tetahomework1.model.dto.MovieDto
import ru.avtoropova.tetahomework1.model.features.movies.MoviesDataSourceImpl
import ru.avtoropova.tetahomework1.view.fragments.MoviesListFragment

typealias MyViewState = MoviesListFragment.ViewState

class MoviesModel : ViewModel() {
    private val repo = MoviesDataSourceImpl()

    private val _movies = MutableLiveData<List<MovieDto>>()
    val movies: LiveData<List<MovieDto>>
        get() = _movies

    val viewState: LiveData<MyViewState> get() = _viewState
    private val _viewState = MutableLiveData<MyViewState>()

    init {
        getMovies()
    }

    fun getMoviesShuffle() {
        viewModelScope.launch {
            val movies = repo.getMovies().shuffled()
            _movies.postValue(movies)
        }
    }

    private fun getMovies() {
        viewModelScope.launch {
            val movies = repo.getMovies()
            _movies.postValue(movies)
        }
    }
}