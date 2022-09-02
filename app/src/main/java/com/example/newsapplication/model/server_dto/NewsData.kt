package com.example.newsapplication.model.server_dto

import com.example.newsapplication.model.local_dto.Article
import com.google.gson.annotations.SerializedName

data class NewsData(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: ArrayList<Article>
)