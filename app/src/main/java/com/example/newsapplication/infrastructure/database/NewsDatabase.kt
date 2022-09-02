package com.example.newsapplication.infrastructure.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapplication.model.local_dto.NewsArticle

@Database(entities = [NewsArticle::class], version = 2)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun Dao(): NewsDao
}