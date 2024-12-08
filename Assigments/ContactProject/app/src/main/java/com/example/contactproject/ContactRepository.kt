package com.example.contactproject

class ContactRepository(private val contactDao: ContactDao) {

    suspend fun insert(contact: Contact) {
        contactDao.insertContact(contact)
    }

    suspend fun findContacts(name: String): List<Contact> {
        return contactDao.findContacts(name)
    }

    suspend fun delete(id: Int) {
        contactDao.delete(id)
    }

    suspend fun sortAsc(): List<Contact> {
        return contactDao.sortAsc()
    }

    suspend fun sortDesc(): List<Contact> {
        return contactDao.sortDesc()
    }

    fun getAllContacts(): List<Contact> {
        return contactDao.getAllContacts().value ?: emptyList()
    }
}
