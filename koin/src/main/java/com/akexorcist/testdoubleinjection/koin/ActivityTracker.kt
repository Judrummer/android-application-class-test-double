package com.akexorcist.testdoubleinjection.koin

import android.app.Activity
import android.app.Application
import android.os.Bundle
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class ActivityTracker : KoinComponent {

    private val timer: UserSessionTimer
        get() = get()

    fun init(application: Application) {
        application.registerActivityLifecycleCallbacks(lifecycleCallbacks)
    }

    private val lifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityStarted(activity: Activity) {
            timer.onUserActive()
        }

        override fun onActivityStopped(activity: Activity) {
            timer.onUserInactive()
        }

        override fun onActivityCreated(activity: Activity, bundle: Bundle?) {}

        override fun onActivityResumed(activity: Activity) {}

        override fun onActivityPaused(activity: Activity) {}

        override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}

        override fun onActivityDestroyed(activity: Activity) {}
    }
}
