package ru.luchenkova.rss76.extensions

import android.graphics.Color
import android.view.ViewManager
import android.widget.TextView
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.textColor
import ru.luchenkova.rss76.App

/**
 * Created by Tatyana Luchenkova on 01.12.2017.
 */
inline fun ViewManager.styledTextView(theme: Int = 0): TextView {
    return styledTextView(theme) {}
}

inline fun ViewManager.styledTextView(theme: Int = 0, init: TextView.() -> Unit): TextView {
    return ankoView(::TextView, theme) {
        textColor = Color.WHITE
        typeface = App.typeface
        init()
    }
}