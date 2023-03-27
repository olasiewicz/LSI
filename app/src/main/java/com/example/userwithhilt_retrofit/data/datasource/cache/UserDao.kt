package com.example.userwithhilt_retrofit.data.datasource.cache

import androidx.room.*
import com.example.userwithhilt_retrofit.domain.model.UserItem

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<UserItem>
//
//    @Query("SELECT * FROM users WHERE id = :id")
//    suspend fun getNoteById(id: Int): NoteCacheEntity?
//
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(note: UserItem)

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insertNotes(notes: List<UserItem>): LongArray

//    @Delete
//    suspend fun deleteNote(note: NoteCacheEntity)
//
//    @Query("UPDATE user SET publisher = :publisher WHERE id = :id")
//    suspend fun updateNote(id: Int, publisher: String)
}