package com.example.newsapplication.infrastructure

import com.example.newsapplication.infrastructure.interfaces.IApiDefenition
import com.example.newsapplication.infrastructure.interfaces.IServerClient
import com.example.newsapplication.model.server_dto.NewsData
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerClient : IServerClient {

    val service : IApiDefenition

    init {
        val gson = GsonBuilder().create()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
            .build()
        service = retrofit.create(IApiDefenition::class.java)
    }

    override suspend fun getNews(): NewsData {
        return try {
            val serverResult : NewsData = service.getNews().await()
            NewsData(serverResult.status, serverResult.totalResults, serverResult.articles)
        } catch (exception : HttpException) {
            NewsData ("HttpException", 0, arrayListOf())
        }
    }

    companion object {
        private const val BASE_URL = "https://saurav.tech/NewsAPI/top-headlines/category/science/"
    }

}