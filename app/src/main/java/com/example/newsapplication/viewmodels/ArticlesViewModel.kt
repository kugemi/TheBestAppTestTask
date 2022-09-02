package com.example.newsapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.model.local_dto.NewsArticle
import com.example.newsapplication.repositories.interfaces.IArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(private val repository: IArticleRepository) : ViewModel() {

    val articles: LiveData<List<ArticleModel>>
        get() = Transformations.map(repository.getArticles()) { articles ->
            return@map articles.map { article ->
                ArticleModel(
                    article.title,
                    article.description,
                    article.urlToImage,
                    article.publishedAt,
                    article.content
                )
            }
        }

    fun clearTable() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearDatabase()
        }
    }

    fun addArticle(article: NewsArticle) : Job {
        return viewModelScope.launch(Dispatchers.IO) {
            repository.addArticle(article)
        }
    }
}