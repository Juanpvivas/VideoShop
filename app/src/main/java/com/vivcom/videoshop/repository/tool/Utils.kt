package com.vivcom.videoshop.repository.tool

import android.content.Context
import android.net.ConnectivityManager


object Utils {

    fun isNetworkAvailable(context: Context): Boolean {
        return try {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            activeNetworkInfo != null && activeNetworkInfo.isConnected
        } catch (e: Exception) {
            false
        }
    }


}