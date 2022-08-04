package com.akexorcist.testdoubleinjection.hilt

import android.util.Log

class UserSessionTimer {
    fun onUserActive() {
        Log.e("Check", "onUserActive")
    }
    fun onUserInactive() {
        Log.e("Check", "onUserInactive")
    }
}
