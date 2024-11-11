package com.example.recycleviews


import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val title = intent.getStringExtra("title")
        val imageRes = intent.getIntExtra("image", R.drawable.android_image_1)
        val text = intent.getStringExtra("text")

        val titleView: TextView = findViewById(R.id.titleView)
        val imageView: ImageView = findViewById(R.id.imageView)
        val textView: TextView = findViewById(R.id.textView)

        titleView.text = title
        imageView.setImageResource(imageRes)
        textView.text = text
    }
}

data class Item(val title: String, val image: Int, val text: String)

object ItemData {
    private val titles = listOf("Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8")
    private val images = listOf(R.drawable.android_image_1, R.drawable.android_image_2, R.drawable.android_image_3, R.drawable.android_image_4, R.drawable.android_image_5, R.drawable.android_image_6, R.drawable.android_image_7, R.drawable.android_image_8) // Drawable resources
    private val texts = listOf("Text for Item 1", "Text for Item 2", "Text for Item 3", "Text for Item 4", "Text for Item 5", "Text for Item 6", "Text for Item 7", "Text for Item 8")

    fun getItems(): List<Item> {
        return titles.indices.map { index ->
            Item(titles[index], images[index], texts[index % texts.size])
        }
    }
}