package com.techbots.latestnews.viewmodel

import androidx.lifecycle.MutableLiveData

class ItemVM: BaseViewModel() {
    private val bitmap = MutableLiveData<String>()

    fun bind(string: String) {
        bitmap.value = string
    }


}