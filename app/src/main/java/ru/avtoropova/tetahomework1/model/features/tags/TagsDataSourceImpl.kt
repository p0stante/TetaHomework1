package ru.avtoropova.tetahomework1.model.features.tags

import ru.avtoropova.tetahomework1.model.dto.TagDto

class TagsDataSourceImpl : TagsDataSource {
    override fun getTags() = listOf(
        TagDto(tag = "боевики"),
        TagDto(tag = "драмы"),
        TagDto(tag = "комедии"),
        TagDto(tag = "артхаус"),
        TagDto(tag = "мелодрамы"),
        TagDto(tag = "триллеры"),
        TagDto(tag = "детективы")
    )
}