package ru.avtoropova.tetahomework1.model.features.tags

import ru.avtoropova.tetahomework1.model.dto.TagDto

interface TagsDataSource {
    fun getTags(): List<TagDto>
}