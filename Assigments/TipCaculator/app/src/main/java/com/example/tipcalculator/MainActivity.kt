package com.example.tipcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalculate.setOnClickListener {
            calculateTip()
        }
    }

    @SuppressLint("DefaultLocale", "SetTextI18n")
    private fun calculateTip() {
        val billTotalStr = binding.editTextBillTotal.text.toString()
        if (billTotalStr.isNotEmpty()) {
            binding.textView.text = "The tips are as follows:"
            val billTotal = billTotalStr.toDouble()
            val tip10 = billTotal * 0.10
            val tip15 = billTotal * 0.15
            val tip20 = billTotal * 0.20

            // Set the calculated tips in the TextViews
            String.format("10%% Tip: $%.2f", tip10).also { binding.textViewTip10.text = it }
            String.format("15%% Tip: $%.2f", tip15).also { binding.textViewTip15.text = it }
            String.format("20%% Tip: $%.2f", tip20).also { binding.textViewTip20.text = it }

        } else {
                binding.textView.text = "You must enter a bill amount"
            }
        }
    }
