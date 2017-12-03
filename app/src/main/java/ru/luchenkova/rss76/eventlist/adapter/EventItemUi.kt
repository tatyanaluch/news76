package ru.luchenkova.rss76.eventlist.adapter

import android.support.v4.content.res.ResourcesCompat
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import org.jetbrains.anko.*
import ru.luchenkova.rss76.R
import ru.luchenkova.rss76.extensions.placeholder
import ru.luchenkova.rss76.extensions.simpleDraweeView
import ru.luchenkova.rss76.extensions.styledTextView

/**
 * Created by Tatyana Luchenkova on 02.12.2017.
 */

class EventItemUi: AnkoComponent<ViewGroup> {

    lateinit var image: SimpleDraweeView
    lateinit var title: TextView

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            relativeLayout {
                image = simpleDraweeView {
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    placeholder = R.drawable.event_placeholder_black
                }.lparams(matchParent, wrapContent)
                title = styledTextView {
                    textSize = sp(10).toFloat()
                    background = ResourcesCompat.getDrawable(resources, R.drawable.event_title_gradient, null)
                    // padding
                    topPadding = dip(20)
                    leftPadding = dip(15)
                    rightPadding = dip(15)
                    bottomPadding = dip(10)
                }.lparams(matchParent, wrapContent) {
                    minimumHeight = dip(80)
                    alignParentBottom()
                }
            }
        }
    }
}