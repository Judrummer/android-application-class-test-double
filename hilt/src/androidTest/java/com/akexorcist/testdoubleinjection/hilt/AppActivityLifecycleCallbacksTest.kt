package com.akexorcist.testdoubleinjection.hilt

import io.mockk.mockk
import io.mockk.verify
import org.junit.Test


class AppActivityLifecycleCallbacksTest {

    private val timer: UserSessionTimer = mockk(relaxUnitFun = true)
    val appActivityLifecycleCallbacks = AppActivityLifecycleCallbacks(timer)

    @Test
    fun onActivityStarted() {
        appActivityLifecycleCallbacks.onActivityStarted(mockk())
        verify { timer.onUserActive() }
    }

    @Test
    fun onActivityStopped() {
        appActivityLifecycleCallbacks.onActivityStopped(mockk())
        verify { timer.onUserInactive() }
    }
}