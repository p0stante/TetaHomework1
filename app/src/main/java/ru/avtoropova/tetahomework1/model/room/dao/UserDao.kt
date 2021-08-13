package ru.avtoropova.tetahomework1.model.room.dao

import androidx.room.*
import ru.avtoropova.tetahomework1.model.room.entities.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Long

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM Users WHERE id LIKE :id")
    fun getUserById(id: Long): User

    @Query("SELECT * FROM Users")
    fun getAll(): List<User>
}