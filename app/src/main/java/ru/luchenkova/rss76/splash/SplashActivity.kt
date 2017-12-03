package ru.luchenkova.rss76.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.startActivity
import ru.luchenkova.rss76.eventlist.EventListActivity

/**
 * Created by Tatyana Luchenkova on 01.12.2017.
 */

class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity<EventListActivity>()
        finish()
    }
}