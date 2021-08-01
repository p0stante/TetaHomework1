package ru.avtoropova.tetahomework1.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.avtoropova.tetahomework1.data.dto.MovieDto
import ru.avtoropova.tetahomework1.features.movies.MoviesDataSource
import ru.avtoropova.tetahomework1.features.movies.MoviesDataSourceImpl
import kotlin.concurrent.thread

class MoviesModel : ViewModel() {
    val mdsi = MoviesDataSourceImpl()

    private val moviesMutableStateFlow = MutableStateFlow<List<MovieDto>>(mdsi.getMovies())
    private val errorMessageMuttable = MutableStateFlow<String>("load movies error")


    val movies: StateFlow<List<MovieDto>> = moviesMutableStateFlow
    val errorMessage: StateFlow<String> = errorMessageMuttable

    fun loadMovies() {
        viewModelScope.launch {
            val localMovies = mdsi.getMovies().shuffled()

            Thread.sleep(3)
            if (localMovies.isNotEmpty()) {
                moviesMutableStateFlow.value = localMovies
            }

            /*val remoteFilmsResult = withContext(Dispatchers.IO) {
                NetworkService.getMovies()
                if (remoteFilmsResult.isNotEmpty()) {
                    filmsMuttableStateFlow.value = remoteFilmsResult
                   }
            }*/

        }

    }
}