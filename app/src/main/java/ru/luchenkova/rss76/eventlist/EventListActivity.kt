package ru.luchenkova.rss76.eventlist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.*
import org.jetbrains.anko.coroutines.experimental.bg
import ru.luchenkova.rss76.R
import ru.luchenkova.rss76.eventlist.adapter.EventListAdapter
import ru.luchenkova.rss76.eventlist.adapter.OnEventClickListener
import ru.luchenkova.rss76.data.EventModel
import ru.luchenkova.rss76.eventdetails.EventDetailsActivity
import ru.luchenkova.rss76.extensions.hasInternetConnection

/**
 * Created by Tatyana Luchenkova on 29.11.2017.
 */

class EventListActivity : AppCompatActivity(), OnEventClickListener {
    companion object {
        private val keyEventList = "keyEventList"
    }

    private lateinit var ui: EventListUi
    private lateinit var interactor: EventListInteractor
    lateinit var adapter: EventListAdapter
        private set
    private var loaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Инициализируем адаптер
        val screenWidth = resources.displayMetrics.widthPixels
        adapter = EventListAdapter(screenWidth, this)
        // Инициализируем ui
        ui = EventListUi(this)
        ui.setContentView(this)
        // Инициализируем интерактор
        interactor = EventListInteractor()
        // Начинаем загружать данные или восстанавливаем их из сохраненного состояния
        if (savedInstanceState != null) {
            val restored = ArrayList<EventModel>(savedInstanceState.getParcelableArrayList(keyEventList))
            if (!restored.isEmpty()) {
                loaded = true
                adapter.content = restored
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (!loaded) {
            updateFromServer(false)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelableArrayList(keyEventList, ArrayList(adapter.content))
    }

    /**
     * Запускаем обновление данных.
     */
    fun updateFromServer(force: Boolean) {
        if (hasInternetConnection(this)) {
            async(UI) {
                // Декларируем процесс загрузки
                val routine = bg {
                    interactor.loadEventList()
                }
                // Показываем спиннер, если пользователь сам обновил список
                if (force) {
                    ui.refresherLayout.isRefreshing = true
                }
                // Показываем loading view с анимацией
                TransitionManager.beginDelayedTransition(ui.contentRoot)
                ui.loadingView.visibility = View.VISIBLE
                // Запускаем загрузку новостей
                adapter.content = routine.await()
                // Выставляем флаг, что загрузка уже прошла
                loaded = true
                // Скрываем спиннер, если пользователь сам обновил список
                if (force) {
                    ui.refresherLayout.isRefreshing = false
                }
                // Скрываем loading view с анимацией
                TransitionManager.beginDelayedTransition(ui.contentRoot)
                ui.loadingView.visibility = View.GONE
            }
        } else {
            ui.refresherLayout.isRefreshing = false
            // Скрываем loading view с анимацией
            TransitionManager.beginDelayedTransition(ui.contentRoot)
            ui.loadingView.visibility = View.GONE
            toast(getString(R.string.check_internet_connection_message))
        }
    }

    /**
     * Действие при клике на новость в ленте.
     */
    override fun onEventClick(event: EventModel) {
        startActivity<EventDetailsActivity>(
                EventDetailsActivity.keyEvent to event
        )
        overridePendingTransition(
                R.anim.slide_in_from_right,
                R.anim.slide_out_to_left
        )
    }


}
