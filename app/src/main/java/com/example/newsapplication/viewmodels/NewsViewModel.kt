package com.example.newsapplication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.model.local_dto.Article
import com.example.newsapplication.repositories.interfaces.INewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: INewsRepository) : ViewModel()  {

    suspend fun getNews() : ArrayList<Article> {
        var articles = arrayListOf<Article>()

        viewModelScope.launch(Dispatchers.IO) {
            articles = repository.getNews().articles
        }.join()

        return articles
    }
}