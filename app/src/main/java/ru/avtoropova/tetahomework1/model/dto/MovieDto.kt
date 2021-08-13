package ru.avtoropova.tetahomework1.model.dto

import java.util.ArrayList

data class MovieDto(
    val title: String?,
    val description: String?,
    val rateScore: Int,
    val ageRestriction: Int,
    val imageUrl: String?,
    val actors: ArrayList<ActorDto>?
)