package com.example.newsapplication.infrastructure.interfaces

import com.example.newsapplication.model.server_dto.NewsData

interface IServerClient {
    suspend fun getNews() : NewsData
}