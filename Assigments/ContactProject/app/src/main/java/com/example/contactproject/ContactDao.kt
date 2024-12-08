package com.example.contactproject

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {
    @Insert
    suspend fun insertContact(contact: Contact)

    @Query("SELECT * FROM contacts WHERE contactName LIKE '%' || :name || '%'")
    suspend fun findContacts(name: String): List<Contact>

    @Query("DELETE FROM contacts WHERE contactId = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): LiveData<List<Contact>>

    @Query("SELECT * FROM contacts ORDER BY contactName ASC")
    suspend fun sortAsc(): List<Contact>

    @Query("SELECT * FROM contacts ORDER BY contactName DESC")
    suspend fun sortDesc(): List<Contact>
}