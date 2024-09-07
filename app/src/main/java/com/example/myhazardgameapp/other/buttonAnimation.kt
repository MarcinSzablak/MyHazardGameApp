package com.example.myhazardgameapp.other

import android.view.View

fun buttonAnimation(it: View) {
    it.animate()
        .scaleX(0.9f)
        .scaleY(0.9f)
        .setDuration(200)
        .withEndAction {
            it.animate()
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(200)
                .start()
        }
        .start()
}