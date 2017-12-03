package ru.luchenkova.rss76.eventlist

import android.app.ActionBar
import android.graphics.Color
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import ru.luchenkova.rss76.extensions.styledTextView

/**
 * Created by Tatyana Luchenkova on 02.12.2017.
 */

class EventListUi(private val activity: EventListActivity): AnkoComponent<EventListActivity> {

    private var toolbarId = 1
    lateinit var contentRoot: ViewGroup
    lateinit var refresherLayout: SwipeRefreshLayout
    lateinit var loadingView: TextView

    override fun createView(ui: AnkoContext<EventListActivity>) = with(ui) {
        relativeLayout {
            // Toolbar
            toolbar {
                id = toolbarId
                backgroundColor = Color.BLACK
                styledTextView {
                    text = "Новости"
                    textSize = sp(10).toFloat()
                }.lparams(wrapContent, wrapContent) {
                    gravity = Gravity.CENTER
                }
            }.lparams(matchParent, wrapContent) {
                alignParentTop()
            }
            // Swipe refresher layout
            refresherLayout = swipeRefreshLayout {
                onRefresh {
                    activity.updateFromServer(true)
                }
                contentRoot = frameLayout {
                    // Loading view
                    loadingView = styledTextView {
                        text = "Загружаем..."
                        textSize = sp(11).toFloat()
                        gravity = Gravity.CENTER
                        visibility = View.GONE
                    }.lparams(matchParent, matchParent)
                    // List view
                    recyclerView {
                        layoutManager = LinearLayoutManager(context)
                        adapter = activity.adapter
                    }.lparams(matchParent, matchParent)
                }
            }.lparams(matchParent, matchParent) {
                below(toolbarId)
            }
        }
    }

}