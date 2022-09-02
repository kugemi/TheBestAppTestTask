package com.example.newsapplication.repositories

import com.example.newsapplication.infrastructure.interfaces.IServerClient
import com.example.newsapplication.model.server_dto.NewsData
import com.example.newsapplication.repositories.interfaces.INewsRepository

class RemoteNewsRepository(private val serverClient: IServerClient) : INewsRepository {
    override suspend fun getNews(): NewsData {
        return serverClient.getNews()
    }
}