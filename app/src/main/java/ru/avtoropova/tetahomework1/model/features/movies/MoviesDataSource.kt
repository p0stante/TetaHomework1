package ru.avtoropova.tetahomework1.model.features.movies

import ru.avtoropova.tetahomework1.model.dto.MovieDto

interface MoviesDataSource {
    fun getMovies(): List<MovieDto>
}