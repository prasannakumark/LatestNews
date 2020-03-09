package com.techbots.latestnews.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import java.text.DateFormat
import java.text.SimpleDateFormat

/** The base URL of the API */
const val API_KEY:String = "9a3708a70e3e4aac8ae23cd0b83b008d"
const val BASE_URL: String = "https://newsapi.org/v2/"
const val EXTRA_OBJECT: String = "extra_object"

fun isOnline(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}

@SuppressLint("SimpleDateFormat")
fun getDate(dateString: String?): String? {
    return try {
        val format = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")
        val date = format.parse(dateString)
        val sdf: DateFormat = SimpleDateFormat("MMM d, yyyy")
        sdf.format(date)
    } catch (ex: Exception) {
        ex.printStackTrace()
        "xx"
    }
}