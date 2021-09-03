package ru.avtoropova.tetahomework1.model.retrofit

import ru.avtoropova.tetahomework1.model.room.entities.Movie

interface MoviesRepoInterface {
    fun getMovies(): List<Movie>
    fun getNextMovies(): List<Movie>
    fun getActorsAndTags(id: Long): GetActorsTagsResponse

}