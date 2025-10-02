package com.example.databases.db.database;

import androidx.room.Database;

import com.example.databases.db.entities.Contact;

@Database() entities = [Contact::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDAO
}