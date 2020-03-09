package com.techbots.latestnews.view

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.techbots.latestnews.R
import com.techbots.latestnews.databinding.NewsFragmentBinding
import com.techbots.latestnews.di.ViewModelFactory
import com.techbots.latestnews.viewmodel.HomePageViewModel

class MainActivity : AppCompatActivity() , HomePageViewModel.CallBacks{
    private lateinit var binding: NewsFragmentBinding
    private lateinit var viewModel: HomePageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.news_fragment
        )
        binding.newsArticlesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this,
            ViewModelFactory(this)
        ).get(HomePageViewModel::class.java)
        binding.newsArticlesList.addOnScrollListener(viewModel.recyclerListener)
        binding.viewModel = viewModel
        HomePageViewModel.setCallBacks(viewModel, this)
        viewModel.makeServerRequest()
    }

    /**
     * This method is to display dialog for no network available
     * If no net work available we will be display offline data
     */
    override fun networkError() {
        // build alert dialog
        val dialogBuilder = AlertDialog.Builder(this)

        // set message of alert dialog
        dialogBuilder.setMessage(getString(R.string.only_offine_data))
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton("Ok", DialogInterface.OnClickListener {
                    dialog, id -> viewModel.loadOfflineArticles()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle(getString(R.string.network_error))
        // show alert dialog
        alert.show()
    }
}
