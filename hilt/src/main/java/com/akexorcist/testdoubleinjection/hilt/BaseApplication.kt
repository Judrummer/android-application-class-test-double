package com.akexorcist.testdoubleinjection.hilt

import android.app.Application
import dagger.hilt.InstallIn
import dagger.hilt.android.EarlyEntryPoint
import dagger.hilt.android.EarlyEntryPoints
import dagger.hilt.components.SingletonComponent

open class BaseApplication : Application() {
    @EarlyEntryPoint
    @InstallIn(SingletonComponent::class)
    interface EntryPoint {
        fun tracker(): ActivityTracker
    }

    lateinit var tracker: ActivityTracker

    override fun onCreate() {
        super.onCreate()
        tracker = EarlyEntryPoints.get(this, EntryPoint::class.java).tracker()
        tracker.init(this)
    }
}
