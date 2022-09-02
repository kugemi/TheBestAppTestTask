package com.example.newsapplication.repositories

import androidx.lifecycle.LiveData
import com.example.newsapplication.infrastructure.database.NewsDatabase
import com.example.newsapplication.model.local_dto.NewsArticle
import com.example.newsapplication.repositories.interfaces.IArticleRepository

class LocalArticlesRepository(private val database: NewsDatabase) : IArticleRepository {
    override fun getArticles(): LiveData<List<NewsArticle>> {
        return database.Dao().fetchedNews()
    }

    override suspend fun clearDatabase() {
        return database.Dao().clearTable()
    }

    override suspend fun addArticle(article: NewsArticle) {
        return database.Dao().addArticle(article)
    }

}