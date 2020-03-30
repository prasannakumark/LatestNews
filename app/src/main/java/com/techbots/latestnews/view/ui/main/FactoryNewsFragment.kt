package com.techbots.latestnews.view.ui.main

import androidx.fragment.app.Fragment

class FactoryNewsFragment {

    companion object{
        fun getNewsFragment(position:Int, newsCategory:String) : Fragment {
            when(newsCategory) {
                "My City" ->
                    return NewsEverythingFragment.newInstance(position, newsCategory)
                "For You" ->
                    return NewsCountryFragment.newInstance(position, newsCategory)
                else ->
                    return NewsCatergeryFragment.newInstance(position,newsCategory)
            }
        }
    }
}