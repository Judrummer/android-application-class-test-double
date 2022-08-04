package com.akexorcist.testdoubleinjection.koin

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class UserSessionUiTest {
    @MockK
    lateinit var userSessionTimer: UserSessionTimer

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        val injectedModules = module {
            single { userSessionTimer }
            single { ActivityTracker() }
        }
        unloadKoinModules(appModule)
        loadKoinModules(listOf(appModule, injectedModules))

        every { userSessionTimer.onUserActive() } just Runs
        every { userSessionTimer.onUserInactive() } just Runs
    }

    @Test
    fun case1() {
        launchActivity<MainActivity>().use {
            it.moveToState(Lifecycle.State.STARTED)
            verify(exactly = 1) { userSessionTimer.onUserActive() }
            it.moveToState(Lifecycle.State.DESTROYED)
            verify(exactly = 1) { userSessionTimer.onUserInactive() }
        }
    }

    @Test
    fun case2() {
        launchActivity<MainActivity>().use {
            it.moveToState(Lifecycle.State.STARTED)
            verify(exactly = 1) { userSessionTimer.onUserActive() }
            it.moveToState(Lifecycle.State.RESUMED)
            verify(exactly = 0) { userSessionTimer.onUserInactive() }
        }
    }
}
