package com.example.newsapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.newsapplication.adapters.*
import com.example.newsapplication.databinding.ArticleItemBinding
import com.example.newsapplication.databinding.FragmentNewsBinding
import com.example.newsapplication.viewmodels.ArticleModel
import com.example.newsapplication.viewmodels.ArticlesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.ZonedDateTime

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private val articlesViewModel : ArticlesViewModel by viewModels()

    private lateinit var binding: FragmentNewsBinding

    private val adapter =
        SimpleListAdapter(
            HolderCreator(::createHolder),
            HolderBinder(::bindHolder),
            ArticleDiffCallback()
        )

    private fun bindHolder(model: ArticleModel, holder: Holder<ArticleItemBinding>) {
        holder.binding.viewModel = model
        holder.binding.mViewModel = articlesViewModel
        holder.binding.root.setOnClickListener {
            val action = NewsFragmentDirections.actionNewsFragmentToArticleFragment(model)
            findNavController().navigate(action)
        }

    }

    private fun createHolder(parent: ViewGroup) : Holder<ArticleItemBinding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ArticleItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        binding.viewModel = articlesViewModel
        binding.lifecycleOwner =  viewLifecycleOwner
        binding.newsList.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var allArticles = listOf<ArticleModel>()

        articlesViewModel.articles.observe(viewLifecycleOwner) { articles ->
            allArticles = articles.sortedBy {
                ZonedDateTime.parse(it.publishedAt)
            }
            adapter.submitList(allArticles)
        }

        binding.newsSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    adapter.submitList(allArticles.filter { article ->
                        article.title.contains(query) || article.description.contains(query) || article.content.contains(query)
                    })
                } else adapter.submitList(allArticles)
                return true
            }
        })
    }
}