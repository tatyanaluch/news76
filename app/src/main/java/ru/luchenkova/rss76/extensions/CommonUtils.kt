package ru.luchenkova.rss76.extensions

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Tatyana Luchenkova on 30.11.2017.
 */
fun hasInternetConnection(context: Context): Boolean {
    val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
    if (manager is ConnectivityManager) {
        val info = manager.activeNetworkInfo
        return info != null && info.isConnectedOrConnecting
    }
    return false
}