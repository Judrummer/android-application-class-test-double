package com.akexorcist.testdoubleinjection.hilt

import android.app.Activity
import android.app.Application
import android.os.Bundle
import javax.inject.Inject

class ActivityTracker @Inject constructor(
    private val timer: UserSessionTimer
) {
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
