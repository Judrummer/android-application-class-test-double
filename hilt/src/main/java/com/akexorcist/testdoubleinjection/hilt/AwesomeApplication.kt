package com.akexorcist.testdoubleinjection.hilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class AwesomeApplication : Application() {
//    @Inject
//    lateinit var tracker: ActivityTracker

    @Inject
    lateinit var appActivityLifecycleCallbacks: AppActivityLifecycleCallbacks

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(appActivityLifecycleCallbacks)
//        tracker.init(this)
    }
}
