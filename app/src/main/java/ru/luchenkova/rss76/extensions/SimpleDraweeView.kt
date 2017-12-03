package ru.luchenkova.rss76.extensions

import android.net.Uri
import android.view.ViewManager
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.request.ImageRequestBuilder
import org.jetbrains.anko.custom.ankoView

/**
 * Created by Tatyana Luchenkova on 29.11.2017.
 */

val ASPECT_RATION = 1.3f

inline fun ViewManager.simpleDraweeView(theme: Int = 0) = simpleDraweeView(theme) {}
inline fun ViewManager.simpleDraweeView(theme: Int = 0, init: SimpleDraweeView.() -> Unit): SimpleDraweeView {
    return ankoView(::SimpleDraweeView, theme) {
        aspectRatio = ASPECT_RATION
        init()
    }
}

var SimpleDraweeView.placeholder get() = 1
    set(value) {
        hierarchy.setPlaceholderImage(value)
    }

fun SimpleDraweeView.setImageUrl(uri: String?, maxWidth: Int) {
    val request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
    request.resizeOptions = ResizeOptions(maxWidth, Math.round(maxWidth * ASPECT_RATION))
    controller = Fresco.newDraweeControllerBuilder()
            .setOldController(controller)
            .setImageRequest(request.build())
            .build()
}