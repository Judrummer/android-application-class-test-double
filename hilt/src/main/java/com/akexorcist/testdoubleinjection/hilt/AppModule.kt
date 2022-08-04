package com.akexorcist.testdoubleinjection.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideActivityTracker(timer: UserSessionTimer) = ActivityTracker(timer)

//    @Singleton
//    @Provides
//    fun provideUserSessionTimer(): UserSessionTimer = UserSessionTimer()
}

@Module
@InstallIn(SingletonComponent::class)
class AppModule2 {
//    @Singleton
//    @Provides
//    fun provideActivityTracker(timer: UserSessionTimer) = ActivityTracker(timer)

    @Singleton
    @Provides
    fun provideUserSessionTimer(): UserSessionTimer = UserSessionTimer()
}
