package com.myboard.presentation.di

import android.content.Context
import androidx.room.Room
import com.myboard.data.source.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context) : AppDatabase{
        return Room.databaseBuilder(context,AppDatabase::class.java,"myBoard.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}