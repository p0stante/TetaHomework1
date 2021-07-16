package ru.avtoropova.tetahomework1

import ru.avtoropova.tetahomework1.features.tags.TagsDataSource

class TagsModel(
    private val tagsDataSource: TagsDataSource
) {

    fun getTags() = tagsDataSource.getTags()
}