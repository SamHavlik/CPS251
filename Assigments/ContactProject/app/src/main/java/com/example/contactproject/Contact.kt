package com.example.contactproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) val contactId: Int = 0,
    @ColumnInfo(name = "contactName") val contactName: String,
    @ColumnInfo(name = "contactPhone") val contactPhone: String
)


