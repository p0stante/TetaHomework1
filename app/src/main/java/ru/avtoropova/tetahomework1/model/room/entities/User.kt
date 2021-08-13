package ru.avtoropova.tetahomework1.model.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var name: String = "",
    var password: String = "",
    var mail: String = "",
    var phone: String = "",
    var imageUrl: String = ""
)