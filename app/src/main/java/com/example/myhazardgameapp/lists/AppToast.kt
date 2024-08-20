package com.example.myhazardgameapp.lists

import android.content.Context
import android.widget.Toast


object AppToast {
    private var toast: Toast? = null

    @Synchronized
    fun showToast(context: Context?, message: String?) {
        if (context == null || message == null) return

        // Cancel the previous toast if it’s still being shown
        toast?.cancel()

        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast?.show()
    }
}