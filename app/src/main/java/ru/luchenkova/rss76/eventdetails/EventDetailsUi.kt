package ru.luchenkova.rss76.eventdetails

import android.graphics.Color
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import ru.luchenkova.rss76.R
import ru.luchenkova.rss76.extensions.placeholder
import ru.luchenkova.rss76.extensions.simpleDraweeView
import ru.luchenkova.rss76.extensions.styledTextView

/**
 * Created by Tatyana Luchenkova on 02.12.2017.
 */

class EventDetailsUi(private val activity: EventDetailsActivity,
                     private val toolbarTitleString: String):
        AnkoComponent<EventDetailsActivity> {

    lateinit var title: TextView
    lateinit var image: SimpleDraweeView
    lateinit var date: TextView
    lateinit var description: TextView
    lateinit var viewSource: TextView

    override fun createView(ui: AnkoContext<EventDetailsActivity>) = with(ui) {
        verticalLayout {
            // Toolbar
            toolbar {
                backgroundColor = Color.BLACK
                imageView {
                    isClickable = true
                    imageResource = R.drawable.ic_arrow_left_white
                    onClick {
                        activity.onBackPressed()
                    }
                }.lparams(wrapContent, wrapContent) {
                    gravity = Gravity.START
                }
                styledTextView {
                    text = toolbarTitleString
                    textSize = sp(10).toFloat()
                }.lparams(wrapContent, wrapContent) {
                    gravity = Gravity.CENTER
                }
            }
            // Content
            scrollView {
                verticalLayout {
                    title = styledTextView {
                        textSize = sp(12).toFloat()
                    }.lparams(matchParent, wrapContent) {
                        margin = dip(15)
                    }

                    image = simpleDraweeView {
                        scaleType = ImageView.ScaleType.CENTER_CROP
                        placeholder = R.drawable.event_placeholder_black
                    }.lparams(matchParent, wrapContent)

                    date = styledTextView()
                            .lparams(matchParent, wrapContent) {
                                margin = dip(10)
                            }

                    description = styledTextView {
                        textSize = sp(9).toFloat()
                    }.lparams(matchParent, wrapContent) {
                        margin = dip(10)
                        bottomMargin = 0
                    }

                    viewSource = styledTextView {
                        textColor = Color.BLUE
                        textSize = sp(9).toFloat()
                        text = "Посмотреть источник"
                        gravity = Gravity.CENTER_HORIZONTAL
                        padding = dip(15)
                    }.lparams(matchParent, wrapContent)
                }
            }
        }
    }
}