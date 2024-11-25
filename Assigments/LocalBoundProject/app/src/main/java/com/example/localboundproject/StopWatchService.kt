package com.example.localboundproject

import android.os.Handler
import android.os.Looper

class StopWatchService {

    private var isRunning = false
    private var timerSeconds = 0
    private val handler = Handler(Looper.getMainLooper())
    private var startTime = System.currentTimeMillis()

    private val runnable = object : Runnable {
        override fun run() {
            val now = System.currentTimeMillis()
            timerSeconds += ((now - startTime) / 1000).toInt()
            startTime = now
            handler.postDelayed(this, 1000)
        }
    }

    fun startTimer() {
        if (!isRunning) {
            startTime = System.currentTimeMillis()
            handler.postDelayed(runnable, 1000)
            isRunning = true
        }
    }

    fun pauseTimer() {
        if (isRunning) {
            handler.removeCallbacks(runnable)
            isRunning = false
        }
    }

    fun resetTimer() {
        handler.removeCallbacks(runnable)
        isRunning = false
        timerSeconds = 0
    }

    fun getElapsedTime(): Int {
        return timerSeconds
    }

    fun isRunning(): Boolean {
        return isRunning
    }

    fun restoreState(savedTimerSeconds: Int, wasRunning: Boolean) {
        timerSeconds = savedTimerSeconds
        isRunning = wasRunning
        if (wasRunning) {
            startTimer()
        }
    }
}
