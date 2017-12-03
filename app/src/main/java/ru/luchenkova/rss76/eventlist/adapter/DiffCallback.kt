package ru.luchenkova.rss76.eventlist.adapter

import android.support.v7.util.DiffUtil
import android.text.TextUtils
import ru.luchenkova.rss76.data.EventModel

/**
 * Created by Tatyana Luchenkova on 30.11.2017.
 */

class DiffCallback(val oldList: List<EventModel>, val newList: List<EventModel>): DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return TextUtils.equals(oldItem.guid, newItem.guid)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return TextUtils.equals(oldItem.title, newItem.title)
                && TextUtils.equals(oldItem.enclosureUrl, newItem.enclosureUrl)
    }

}