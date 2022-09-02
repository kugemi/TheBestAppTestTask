package com.example.newsapplication.model.local_dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ARTICLES")
class NewsArticle {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var title: String = ""
    var description: String = ""
    var urlToImage: String = ""
    var publishedAt: String = ""
    var content: String = ""
}