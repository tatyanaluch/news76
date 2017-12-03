package ru.luchenkova.rss76.eventlist.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by Tatyana Luchenkova on 02.12.2017.
 */

class EventItemViewHolder : RecyclerView.ViewHolder {
    val ui: EventItemUi

    constructor(ui: EventItemUi, itemView: View, listener: OnItemClickListener): super(itemView) {
        this.ui = ui
        itemView.onClick { listener.onItemClick(adapterPosition) }
    }
}
