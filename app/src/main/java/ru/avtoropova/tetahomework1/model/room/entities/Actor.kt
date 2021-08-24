package ru.avtoropova.tetahomework1.model.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Actors")
data class Actor(
    @SerializedName("name")
    val name: String?,
    @SerializedName("profile_path")
    var imageUrl: String?,
    @SerializedName("id")
    @PrimaryKey val actorId: Long
)
