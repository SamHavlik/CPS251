package com.example.localboundproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.localboundproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val stopwatchService = StopWatchService()
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Restore the state on rotation
        if (savedInstanceState != null) {
            stopwatchService.restoreState(
                savedInstanceState.getInt("TIMER_SECONDS", 0),
                savedInstanceState.getBoolean("IS_RUNNING", false)
            )
        }

        setupButtons()
        updateElapsedTime()
    }

    private fun setupButtons() {
        binding.button.setOnClickListener {
            stopwatchService.startTimer()
        }

        binding.button2.setOnClickListener {
            stopwatchService.pauseTimer()
        }

        binding.button3.setOnClickListener {
            stopwatchService.resetTimer()
        }
    }

    @SuppressLint("DefaultLocale")
    private fun updateElapsedTime() {
        handler.postDelayed({
            val elapsedTime = stopwatchService.getElapsedTime()
            val hours = elapsedTime / 3600
            val minutes = (elapsedTime % 3600) / 60
            val seconds = elapsedTime % 60

            val time = String.format("%02d:%02d:%02d", hours, minutes, seconds)
            binding.timeTextView.text = time

            // Continue updating every second
            updateElapsedTime()
        }, 1000)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("TIMER_SECONDS", stopwatchService.getElapsedTime())
        outState.putBoolean("IS_RUNNING", stopwatchService.isRunning())
    }
}



