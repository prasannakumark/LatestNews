package com.techbots.latestnews.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.techbots.latestnews.R
import com.techbots.latestnews.databinding.ActivityNewsDetailsBinding
import com.techbots.latestnews.datasource.NewsArticle
import com.techbots.latestnews.di.ViewModelFactory
import com.techbots.latestnews.utils.EXTRA_OBJECT
import com.techbots.latestnews.view.ui.main.NewsCatergeryFragment
import com.techbots.latestnews.view.ui.main.NewsCountryFragment
import com.techbots.latestnews.viewmodel.ArticleViewModel
import com.techbots.latestnews.viewmodel.NewsArticleViewModel

class NewsDetailsActivity : AppCompatActivity() {
    private lateinit var article: NewsArticle
    private lateinit var binding: ActivityNewsDetailsBinding
    private lateinit var articleViewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_news_details
        )
        
        initView()
    }

    fun initView() {
        article = intent.getSerializableExtra(EXTRA_OBJECT) as NewsArticle
        articleViewModel = ViewModelProviders.of(this,
            ViewModelFactory(this)
        ).get(ArticleViewModel::class.java)
        articleViewModel.bind(article)
        binding.viewModel = articleViewModel

        binding.newsArticlesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val homePageViewModel = ViewModelProviders.of(this,
            ViewModelFactory(this)
        ).get(NewsArticleViewModel::class.java)
        binding.homepageViewModel = homePageViewModel
        binding.newsArticlesList.adapter = homePageViewModel.newArticleListAdapter
        homePageViewModel.getNewsByCountry("in")
        homePageViewModel.getPosts().observe(this, Observer {
            Log.v(NewsCatergeryFragment::class.simpleName, "NewsCatergery Fragment " + it.size)
            homePageViewModel.newArticleListAdapter.submitList(it)
        })

        binding.newsArticlesList1.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val homePageViewModel1 = ViewModelProviders.of(this,
            ViewModelFactory(this)
        ).get(NewsArticleViewModel::class.java)
        binding.homepageViewModel1 = homePageViewModel1
        binding.newsArticlesList1.adapter = homePageViewModel1.newArticleListAdapter
        homePageViewModel1.getNewsByCategory("Business")
        homePageViewModel1.getPosts().observe(this, Observer {
            Log.v(NewsCatergeryFragment::class.simpleName, "NewsCatergery Fragment " + it.size)
            homePageViewModel1.newArticleListAdapter.submitList(it)
        })
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        initView()
    }
}
