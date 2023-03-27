package com.example.userwithhilt_retrofit.di

import android.app.Application
import androidx.room.Room
import com.example.userwithhilt_retrofit.data.datasource.cache.UserDao
import com.example.userwithhilt_retrofit.data.datasource.cache.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideUserDatabase(app: Application): UserDatabase {
        return Room.databaseBuilder(
            app,
            UserDatabase::class.java,
            UserDatabase.DATABASE_NAME
        ).build()
    }
    @Singleton
    @Provides
    fun provideNoteDao(db: UserDatabase): UserDao {
        return db.userDao
    }

}