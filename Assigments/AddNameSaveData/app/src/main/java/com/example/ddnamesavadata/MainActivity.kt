package com.example.ddnamesavadata

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val enterName = findViewById<EditText>(R.id.textInputEditText)
        val btnAddName = findViewById<Button>(R.id.button)
        val displayNames = findViewById<TextView>(R.id.displayNames)

        viewModel.names.observe(this) { names ->
            if (names.isEmpty()) {
                displayNames.text = "No names to display"
            } else {
                displayNames.text = names.joinToString("\n")
            }
        }

        btnAddName.setOnClickListener {
            val enteredName = enterName.text.toString()
            if (enteredName.isBlank()) {
                displayNames.text = "No name entered"
            } else {
                viewModel.addName(enteredName)
                enterName.text.clear()
            }
        }
    }
}