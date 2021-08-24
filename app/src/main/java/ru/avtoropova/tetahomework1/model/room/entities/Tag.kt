package ru.avtoropova.tetahomework1.model.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Tags")
data class Tag(
    @SerializedName("id")
    @PrimaryKey val tagId: Long,
    @SerializedName("name")
    val tagName: String
)