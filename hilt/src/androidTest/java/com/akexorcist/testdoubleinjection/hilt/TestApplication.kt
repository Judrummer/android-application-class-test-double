package com.akexorcist.testdoubleinjection.hilt

import android.app.Application
import dagger.hilt.android.testing.CustomTestApplication
import javax.inject.Inject


class TestAwesomeApplication : Application() {
    @Inject
    lateinit var tracker: ActivityTracker

    override fun onCreate() {
        super.onCreate()
        tracker.init(this)
    }
}

@CustomTestApplication(TestAwesomeApplication::class)
interface CustomTestApplication


