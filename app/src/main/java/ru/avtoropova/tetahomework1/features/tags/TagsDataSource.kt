package ru.avtoropova.tetahomework1.features.tags

import ru.avtoropova.tetahomework1.data.dto.MovieDto
import ru.avtoropova.tetahomework1.data.dto.TagDto

interface TagsDataSource {
    fun getTags(): List<TagDto>
}