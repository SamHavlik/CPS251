package com.example.contactproject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ContactRepository

    init {
        val contactDao = ContactRoomDatabase.getDatabase(application).contactDao()
        repository = ContactRepository(contactDao)
    }

    private val _allContacts = MutableLiveData<List<Contact>>()
    val allContacts: LiveData<List<Contact>> = _allContacts

    init {

        refreshContactList()
    }

    fun addContact(contact: Contact) = viewModelScope.launch {
        repository.insert(contact)
        refreshContactList()
    }

    fun findContacts(name: String) = viewModelScope.launch {
        _allContacts.postValue(repository.findContacts(name))
    }

    fun deleteContact(id: Int) = viewModelScope.launch {
        repository.delete(id)
        refreshContactList()
    }

    fun sortContactsAsc() = viewModelScope.launch {
        _allContacts.postValue(repository.sortAsc())
    }

    fun sortContactsDesc() = viewModelScope.launch {
        _allContacts.postValue(repository.sortDesc())
    }

    private fun refreshContactList() = viewModelScope.launch {
        _allContacts.postValue(repository.getAllContacts())
    }
}

