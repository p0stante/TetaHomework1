package ru.avtoropova.tetahomework1.model.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Actors")
data class Actor(
    val name: String?,
    val imageUrl: String?,
    @PrimaryKey val actorId: Long
)
