package com.akexorcist.testdoubleinjection.hilt

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Singleton

@RunWith(AndroidJUnit4::class)
@UninstallModules(AppModule2::class)
@HiltAndroidTest
class UserSessionUiTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @MockK
    @BindValue
    lateinit var userSessionTimer: UserSessionTimer

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        hiltRule.inject()

        every { userSessionTimer.onUserActive() } just Runs
        every { userSessionTimer.onUserInactive() } just Runs
    }

    @Test
    fun case1() {
        launchActivity<MainActivity>().use {
            it.moveToState(Lifecycle.State.STARTED)
//            verify(exactly = 1) { userSessionTimer.onUserActive() }
            it.moveToState(Lifecycle.State.DESTROYED)
//            verify(exactly = 1) { userSessionTimer.onUserInactive() }
        }
    }

    @Test
    fun case2() {
        launchActivity<MainActivity>().use {
            it.moveToState(Lifecycle.State.STARTED)
//            verify(exactly = 1) { userSessionTimer.onUserActive() }
            it.moveToState(Lifecycle.State.RESUMED)
//            verify(exactly = 0) { userSessionTimer.onUserInactive() }
        }
    }
}

