package ru.luchenkova.rss76.data

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Tatyana Luchenkova on 28.11.2017.
 */

@SuppressLint("ParcelCreator")
@Parcelize
data class EventModel(val guid: String,
                      val title: String,
                      val enclosureUrl: String?,
                      val link: String,
                      val category: String,
                      val description: String,
                      val date: Long): Parcelable