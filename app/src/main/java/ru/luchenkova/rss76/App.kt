package ru.luchenkova.rss76

import android.app.Application
import android.graphics.Typeface
import com.facebook.drawee.backends.pipeline.Fresco
import okhttp3.OkHttpClient
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import ru.luchenkova.rss76.data.api.Ru76Api

/**
 * Created by Tatyana Luchenkova on 28.11.2017.
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initApi()
        initFresco()
        initFont()
    }

    private fun initApi() {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://76.ru/")
                .client(OkHttpClient())
                .addConverterFactory(
                        SimpleXmlConverterFactory.createNonStrict(
                                Persister(AnnotationStrategy())
                        )
                ).build()
        api = retrofit.create(Ru76Api::class.java)
    }

    private fun initFresco() {
        Fresco.initialize(this)
    }

    private fun initFont() {
        val assetManager = applicationContext.assets
        typeface = Typeface.createFromAsset(assetManager, "fonts/Raleway-Regular.ttf")
    }

    companion object {
        lateinit var api: Ru76Api
        lateinit var typeface: Typeface
    }

}