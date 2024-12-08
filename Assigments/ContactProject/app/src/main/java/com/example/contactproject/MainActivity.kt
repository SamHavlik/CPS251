package com.example.contactproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ContactListAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = ContactListAdapter { id -> viewModel.deleteContact(id) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.allContacts.observe(this) { contacts ->
            adapter.submitList(contacts)
        }

        findViewById<Button>(R.id.addButton).setOnClickListener {
            val name = findViewById<EditText>(R.id.nameInput).text.toString()
            val phone = findViewById<EditText>(R.id.phoneInput).text.toString()
            if (name.isNotBlank() && phone.isNotBlank()) {
                viewModel.addContact(Contact(contactName = name, contactPhone = phone))
            } else {
                showToast("Please enter a name and phone number.")
            }
        }

        findViewById<Button>(R.id.findButton).setOnClickListener {
            val name = findViewById<EditText>(R.id.nameInput).text.toString()
            if (name.isNotBlank()) {
                viewModel.findContacts(name)
            } else {
                showToast("Please enter search criteria.")
            }
        }

        findViewById<Button>(R.id.sortAscButton).setOnClickListener {
            viewModel.sortContactsAsc()
        }

        findViewById<Button>(R.id.sortDescButton).setOnClickListener {
            viewModel.sortContactsDesc()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
