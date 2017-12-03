package ru.luchenkova.rss76.eventdetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import ru.luchenkova.rss76.R
import ru.luchenkova.rss76.data.EventModel
import ru.luchenkova.rss76.extensions.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Tatyana Luchenkova on 30.11.2017.
 */
class EventDetailsActivity: AppCompatActivity() {
    companion object {
        val keyEvent = "keyEvent"
    }

    private lateinit var ui: EventDetailsUi
    private lateinit var event: EventModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Получаем данные для отображения
        event = intent.extras.getParcelable(keyEvent)
        // Инициализируем ui
        ui = EventDetailsUi(this, event.category)
        ui.setContentView(this)
        // Сеттим данные для отображения во вью
        setContentToView(event)
    }

    private fun setContentToView(event: EventModel) {
        ui.title.text = event.title
        val dateFormat = SimpleDateFormat("hh:mm EEEE, d MMMM", Locale.getDefault())
        ui.date.text = dateFormat.format(event.date)
        ui.description.text = event.description
        val screenWidth = resources.displayMetrics.widthPixels
        ui.image.setImageUrl(event.enclosureUrl, screenWidth)
        ui.viewSource.onClick {
            browse(event.link, false)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(
                R.anim.slide_in_from_left,
                R.anim.slide_out_to_right
        )
    }

}