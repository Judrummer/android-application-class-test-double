package com.akexorcist.testdoubleinjection.koin

import android.app.Application
import org.koin.android.ext.android.inject
import org.koin.core.context.startKoin

class AwesomeApplication : Application() {
    private val tracker: ActivityTracker by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
        tracker.init(this)
    }
}
