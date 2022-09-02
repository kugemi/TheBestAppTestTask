package com.example.newsapplication.infrastructure.interfaces

import com.example.newsapplication.model.server_dto.NewsData
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface IApiDefenition {
    @GET("in.json")
    fun getNews() : Deferred<NewsData>
}