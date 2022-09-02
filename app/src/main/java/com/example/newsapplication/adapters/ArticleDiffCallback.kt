package com.example.newsapplication.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.newsapplication.viewmodels.ArticleModel

class ArticleDiffCallback : DiffUtil.ItemCallback<ArticleModel>() {
    override fun areItemsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
        return oldItem.publishedAt == newItem.publishedAt && oldItem.title == oldItem.title
    }

    override fun areContentsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
        return oldItem.content == newItem.content
    }
}