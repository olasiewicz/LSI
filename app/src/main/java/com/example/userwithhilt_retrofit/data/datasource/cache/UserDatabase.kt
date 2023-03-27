package com.example.userwithhilt_retrofit.data.datasource.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.userwithhilt_retrofit.domain.model.UserItem

@Database(
    entities = [UserItem::class],
    version = 1
)
abstract class UserDatabase: RoomDatabase() {

    abstract val userDao: UserDao

    companion object {
        const val DATABASE_NAME = "users_db"
    }
}