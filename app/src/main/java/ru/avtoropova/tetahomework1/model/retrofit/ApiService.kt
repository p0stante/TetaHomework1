package ru.avtoropova.tetahomework1.model.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular?language=ru")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): GetMoviesResponse

    @GET("movie/{movie_id}/release_dates")
    suspend fun getAgeRestriction(
        @Path("movie_id") id: Long
    ): GetAgeRestrictionResponse

    @GET("movie/{movie_id}?append_to_response=credits&language=ru")
    suspend fun getActorsAndTags(
        @Path("movie_id") id: Long
    ): GetActorsTagsResponse

    @GET("genre/movie/list?language=ru")
    suspend fun getTags(): GetTagsResponse


}