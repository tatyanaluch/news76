package ru.luchenkova.rss76.eventlist.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import org.jetbrains.anko.*
import ru.luchenkova.rss76.data.EventModel
import ru.luchenkova.rss76.extensions.setImageUrl

/**
 * Created by Tatyana Luchenkova on 28.11.2017.
 */

interface OnEventClickListener {
    fun onEventClick(event: EventModel)
}

interface OnItemClickListener {
    fun onItemClick(position: Int)
}

class EventListAdapter(private val maxImageWidth: Int, private val listener: OnEventClickListener):
        RecyclerView.Adapter<EventItemViewHolder>(), OnItemClickListener {

    var content: List<EventModel> = emptyList()
        set(value) {
            // Вычисляем разницу списков
            val diffCallback = DiffCallback(field, value)
            val diff = DiffUtil.calculateDiff(diffCallback)
            // Присваиваем новый контент
            field = value
            // Применяем изменения к списку
            diff.dispatchUpdatesTo(this)
        }

    override fun getItemCount() = content.size

    override fun onBindViewHolder(holder: EventItemViewHolder, position: Int) {
        val item = content[position]
        holder.ui.image.setImageUrl(item.enclosureUrl, maxImageWidth)
        holder.ui.title.text = item.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventItemViewHolder {
        val ui = EventItemUi()
        val view = ui.createView(AnkoContext.Companion.create(parent.context, parent))
        return EventItemViewHolder(ui, view, this)
    }

    override fun onItemClick(position: Int) {
        listener.onEventClick(content[position])
    }

}