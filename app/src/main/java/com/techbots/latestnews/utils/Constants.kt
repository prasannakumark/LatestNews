package com.techbots.latestnews.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import java.text.DateFormat
import java.text.SimpleDateFormat

/** The base URL of the API */
const val BASE_URL: String = "https://dog.ceo/api/breeds/image/"

fun isOnline(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}