package com.example.databases.db.daos;

import androidx.room.Dao;
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query;
import com.example.databases.db.entities.Contact

@Dao
interface UserDao {
    @Query("SELECT * FROM contact")
    fun getAll(): List<Contact>

    @Insert
    fun insert(contacts: Contact)

    @Delete
    fun delete(contact: Contact)
}
