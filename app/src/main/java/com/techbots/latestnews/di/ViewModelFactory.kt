package com.techbots.latestnews.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.techbots.latestnews.viewmodel.HomepageVM
import com.techbots.latestnews.viewmodel.ImageGenerateVM

/**
 * This is class for get exact view model based on the type of activity
 */
class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomepageVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomepageVM(activity) as T
        } else if (modelClass.isAssignableFrom(ImageGenerateVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ImageGenerateVM(activity) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}