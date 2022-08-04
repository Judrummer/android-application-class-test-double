package com.akexorcist.testdoubleinjection.koin

import org.koin.dsl.module

val appModule = module {
    single { ActivityTracker() }
    single { UserSessionTimer() }
}
