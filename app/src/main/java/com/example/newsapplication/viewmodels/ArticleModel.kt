package com.example.newsapplication.viewmodels

import java.io.Serializable

data class ArticleModel (
    val title: String,
    val description: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
) : Serializable