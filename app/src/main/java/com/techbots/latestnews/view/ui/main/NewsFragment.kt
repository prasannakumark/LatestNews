package com.techbots.latestnews.view.ui.main

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.techbots.latestnews.R
import com.techbots.latestnews.databinding.NewsFragmentBinding
import com.techbots.latestnews.di.ViewModelFactory
import com.techbots.latestnews.viewmodel.NewsArticleViewModel

class NewsFragment : Fragment(), NewsArticleViewModel.CallBacks{
    private lateinit var binding: NewsFragmentBinding
    private lateinit var viewModel: NewsArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.news_fragment,container, false)
        binding.newsArticlesList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this,
            ViewModelFactory(activity!!)
        ).get(NewsArticleViewModel::class.java)
        binding.newsArticlesList.addOnScrollListener(viewModel.recyclerListener)
        binding.viewModel = viewModel
        binding.newsArticlesList.adapter = viewModel.newArticleListAdapter
        NewsArticleViewModel.setCallBacks(viewModel, this)
        viewModel.makeServerRequest(arguments!!.getString("key","For You"))
        return binding.root
    }

    /**
     * This method is to display dialog for no network available
     * If no net work available we will be display offline data
     */
    override fun networkError() {
        // build alert dialog
        val dialogBuilder = AlertDialog.Builder(activity!!.baseContext)

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

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int, string: String): NewsFragment {
            return NewsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    putString("key", string)
                }
            }
        }
    }
}
