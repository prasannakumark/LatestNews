package com.techbots.latestnews.datasource

import android.content.Context
import com.techbots.latestnews.db.DataRepository

interface DataSource {
    fun getNewsByCountry(uiCallBacks: DataRepository.UICallBacks, countryName:String)
    fun getNewsByEveryThing(uiCallBacks: DataRepository.UICallBacks, query:String)
    fun getNewsByCategory(uiCallBacks: DataRepository.UICallBacks, category:String)
}