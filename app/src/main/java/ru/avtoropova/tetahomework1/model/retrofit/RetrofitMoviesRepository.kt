package ru.avtoropova.tetahomework1.model.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.*
import ru.avtoropova.tetahomework1.model.room.entities.Movie
import ru.avtoropova.tetahomework1.utils.BASE_URL
import ru.avtoropova.tetahomework1.utils.BASE_URL_FOR_IMAGES

class RetrofitMoviesRepository : MoviesRepoInterface {
    private val apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .setClient()
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    var page: Int = 1
    override suspend fun getMovies(): List<Movie> {
        val response = apiService.getPopularMovies(page = page).movies
        return normalizeResults(response)
    }

    private suspend fun normalizeResults(response: List<Movie>): List<Movie> {
        response.forEach {
            it.imageUrl = BASE_URL_FOR_IMAGES + it.imageUrl
            it.ageRestriction = getAgeRestriction(it.movieId)
        }
        return response
    }

    private suspend fun getAgeRestriction(movieId: Long): String {
        val response = apiService.getAgeRestriction(id = movieId).response
        response.forEach {
            if (it.iso == "RU") {

                return it.releaseDates[0].certication.ifEmpty { "18+" }
            }
        }
        return "18+"
    }

    override suspend fun getNextMovies(): List<Movie> {
        page++
        return getMovies()
    }

    override fun getActorsAndTags(id: Long): GetActorsTagsResponse = runBlocking {
        val actorsAndTags = apiService.getActorsAndTags(id)
        actorsAndTags.cast.actors.forEach {
            it.imageUrl = BASE_URL_FOR_IMAGES + it.imageUrl
        }
        return@runBlocking actorsAndTags
    }
}