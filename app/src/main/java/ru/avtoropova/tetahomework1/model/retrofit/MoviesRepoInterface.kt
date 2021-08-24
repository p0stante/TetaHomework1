package ru.avtoropova.tetahomework1.model.retrofit

import ru.avtoropova.tetahomework1.model.room.entities.Movie

interface MoviesRepoInterface {
    suspend fun getMovies(): List<Movie>
    suspend fun getNextMovies(): List<Movie>
    fun getActorsAndTags(id: Long): GetActorsTagsResponse

}