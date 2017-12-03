package ru.luchenkova.rss76.data.api

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Tatyana Luchenkova on 28.11.2017.
 */

interface Ru76Api {

    @GET("/text/rss.xml")
    fun getData(): Call<RSS>

}