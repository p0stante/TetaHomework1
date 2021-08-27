package ru.avtoropova.tetahomework1.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.avtoropova.tetahomework1.model.room.dao.MovieDao
import ru.avtoropova.tetahomework1.model.room.dao.UserDao
import ru.avtoropova.tetahomework1.model.room.entities.*
import ru.avtoropova.tetahomework1.utils.DATABASE_NAME

@Database(
    entities = [User::class, Movie::class, Actor::class, MoviesActorsCrossRef::class,
        Tag::class, MoviesTagsCrossRef::class],
    version = 1
)
abstract class AppDB : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun movieDao(): MovieDao

    companion object {
        private var INSTANCE: AppDB? = null

        fun getAppDB(context: Context): AppDB? {
            if (INSTANCE == null) {
                synchronized(AppDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, AppDB::class.java,
                        DATABASE_NAME
                    ).allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE


        }
    }
}