package ru.avtoropova.tetahomework1.features.movies

import ru.avtoropova.tetahomework1.data.dto.MovieDto

interface MoviesDataSource {
    fun getMovies(): List<MovieDto>
}