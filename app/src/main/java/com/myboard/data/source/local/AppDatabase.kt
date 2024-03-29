package com.myboard.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.myboard.data.model.entity.ContentEntity
import com.myboard.data.source.local.dao.ContentDao

@Database(entities = [ContentEntity::class], version = 4)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contentDao() : ContentDao
}