package com.example.newsapplication.adapters

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class Holder<T : ViewDataBinding>(bind: T) : RecyclerView.ViewHolder(bind.root) {
    val binding = bind
}