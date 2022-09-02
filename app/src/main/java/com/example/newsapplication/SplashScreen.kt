package com.example.newsapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.newsapplication.model.local_dto.NewsArticle
import com.example.newsapplication.viewmodels.ArticlesViewModel
import com.example.newsapplication.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {
    private val newsViewModel : NewsViewModel by viewModels()
    private val articlesViewModel : ArticlesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        articlesViewModel.clearTable()

        lifecycleScope.launch {
            val listOfNews = newsViewModel.getNews()

            val operation = async(Dispatchers.IO) {
                val test1 = 1
                listOfNews.forEach { article ->
                    val test = 0
                    articlesViewModel.addArticle(NewsArticle().apply {
                        this.title = article.title ?: ""
                        this.description = article.description ?: ""
                        this.urlToImage = article.urlToImage ?: ""
                        this.publishedAt = article.publishedAt ?: ""
                        this.content = article.content ?: ""
                    }).join()
                }
            }
            operation.await()

            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}