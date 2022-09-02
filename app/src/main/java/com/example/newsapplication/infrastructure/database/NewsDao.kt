package com.example.newsapplication.infrastructure.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.newsapplication.model.local_dto.NewsArticle

@Dao
abstract class NewsDao {
    @Query("SELECT * FROM ARTICLES")
    abstract fun fetchedNews(): LiveData<List<NewsArticle>>

    @Query("DELETE FROM ARTICLES")
    abstract fun clearTable()

    @Insert
    abstract fun addArticle(article: NewsArticle)
}