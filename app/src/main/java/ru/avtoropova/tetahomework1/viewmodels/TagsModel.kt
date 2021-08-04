package ru.avtoropova.tetahomework1.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.avtoropova.tetahomework1.model.dto.TagDto
import ru.avtoropova.tetahomework1.model.features.tags.TagsDataSourceImpl

class TagsModel : ViewModel() {
    private val tagsDataSource = TagsDataSourceImpl()

    private val _tags = MutableLiveData<List<TagDto>>()
    val tags: LiveData<List<TagDto>>
        get() = _tags

    fun getTags() {
        val tags = tagsDataSource.getTags()
        _tags.value = tags
    }
}