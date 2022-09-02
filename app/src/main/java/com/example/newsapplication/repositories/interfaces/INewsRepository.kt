package com.example.newsapplication.repositories.interfaces

import com.example.newsapplication.model.server_dto.NewsData

interface INewsRepository {
    suspend fun getNews() : NewsData
}