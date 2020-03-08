package com.techbots.latestnews.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.techbots.latestnews.R
import com.techbots.latestnews.databinding.ActivityNewsDetailsBinding
import com.techbots.latestnews.datasource.NewsArticle
import com.techbots.latestnews.di.ViewModelFactory
import com.techbots.latestnews.utils.EXTRA_OBJECT
import com.techbots.latestnews.viewmodel.ArticleViewModel

class NewsDetailsActivity : AppCompatActivity() {

    private lateinit var article: NewsArticle
    private lateinit var binding: ActivityNewsDetailsBinding
    private lateinit var articleViewModel: ArticleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_news_details
        )
        article = intent.getSerializableExtra(EXTRA_OBJECT) as NewsArticle
        articleViewModel = ViewModelProviders.of(this,
            ViewModelFactory(this)
        ).get(ArticleViewModel::class.java)
        articleViewModel.bind(article)
        binding.viewModel = articleViewModel
    }
}
