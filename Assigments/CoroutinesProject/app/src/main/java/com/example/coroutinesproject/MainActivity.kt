package com.example.coroutinesproject

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coroutinesproject.R.string.result_message
import com.example.coroutinesproject.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random
import kotlin.reflect.KProperty

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var editTextName: EditText
    private lateinit var buttonAddName: Button
    private lateinit var linearLayoutResults: LinearLayout
    private val viewModel: MainViewModel by viewModels()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editTextName = findViewById(R.id.editTextName)
        buttonAddName = findViewById(R.id.buttonAddName)
        linearLayoutResults = findViewById(R.id.linearLayoutResults)

        buttonAddName.setOnClickListener {
            val name = editTextName.text.toString()
            if (name.isNotBlank()) {
                addName(name)
                editTextName.text.clear()
            }
        }
        viewModel.names.observe(this) { names ->
            linearLayoutResults.removeAllViews()
            names.forEach { result ->
                addNameToUI(result)
            }
        }
    }


    @SuppressLint("ResourceAsColor")
    private fun addName(name: String) {
        coroutineScope.launch(Dispatchers.Default) {
            val result = processName(name)
            withContext(Dispatchers.Main) {
                viewModel.addName(result)
            }
        }
    }

    private fun addNameToUI(result: String) {
        val textView = TextView(this, null, 0, R.style.ProcessingTextView)
        textView.text = result
        textView.visibility = View.VISIBLE

        linearLayoutResults.addView(textView)
        linearLayoutResults.addView(createSpacer(), linearLayoutResults.indexOfChild(textView) + 1)
    }

    private fun createSpacer(): View {
        return View(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                resources.getDimensionPixelSize(R.dimen.spacerHeight)
            )
            setBackgroundColor(Color.WHITE)
        }
    }

    private suspend fun processName(name: String): String {
        val processingTime = Random.nextInt(1, 11) * 1000
        delay(processingTime.toLong())
        return getString(result_message, name, processingTime)
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}

class MainViewModel : ViewModel() {
    private val _names = MutableLiveData<List<String>>(emptyList())
    val names: LiveData<List<String>> = _names

    private operator fun Any.getValue(mainActivity: MainActivity, property: KProperty<*>): Any {
        TODO("Not yet implemented")
    }

    fun addName(name: String) {
        _names.value = _names.value?.plus(name)
    }
}

