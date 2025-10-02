package com.example.databases.db.entities;

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
        @PrimaryKey(autoGenerate = true) val id: Int,
        @ColumnInfo(name = "first_name") val nombre: String?,
        @ColumnInfo(name = "last_name") val apellido: String?,
        @ColumnInfo(name = "phone") val telefono: String?
)