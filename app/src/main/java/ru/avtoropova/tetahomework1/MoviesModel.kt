package ru.avtoropova.tetahomework1

import ru.avtoropova.tetahomework1.features.movies.MoviesDataSource

class MoviesModel(
    private val moviesDataSource: MoviesDataSource
) {

    fun getMovies() = moviesDataSource.getMovies()
}