package ru.luchenkova.rss76.eventlist

import ru.luchenkova.rss76.App
import ru.luchenkova.rss76.data.EventModel
import ru.luchenkova.rss76.data.api.RSS
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Tatyana Luchenkova on 29.11.2017.
 */

class EventListInteractor {
    /**
     * Формат даты в xml.
     */
    private val dateFormat = "EEE, dd MMM yyyy HH:mm:ss z"

    /**
     * Парсер даты.
     */
    private val dateParser = SimpleDateFormat(dateFormat, Locale.ENGLISH)

    /**
     * Конвертируем модель из xml во вью-модель
     */
    private fun map(xml: RSS.Channel.Item): EventModel {
        return EventModel(xml.guid,
                xml.title,
                xml.enclosure?.url,
                xml.pdaLink,
                xml.category,
                xml.description,
                dateParser.parse(xml.pubDate).time)
    }

    /**
     * Загружаем список новостей.
     */
    fun loadEventList(): List<EventModel> {
        val request = App.api.getData()
        val response = request.execute()
        val result = response.body()
        if (result != null) {
            val channel = result.channel
            val items = channel.items
            return items.map {
                map(it)
            }
        }
        return emptyList()
    }
}