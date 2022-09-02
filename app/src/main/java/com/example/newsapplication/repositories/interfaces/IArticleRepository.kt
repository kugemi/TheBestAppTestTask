package com.example.newsapplication.repositories.interfaces

import androidx.lifecycle.LiveData
import com.example.newsapplication.model.local_dto.NewsArticle

interface IArticleRepository {
    fun getArticles(): LiveData<List<NewsArticle>>
    suspend fun clearDatabase()
    suspend fun addArticle(article: NewsArticle)
}