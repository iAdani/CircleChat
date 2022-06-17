package com.example.circlechat;

import androidx.room.RoomDatabase;

import com.example.circlechat.Dao.ContactsDao;
import com.example.circlechat.entities.Contact;

@androidx.room.Database(entities = {Contact.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract ContactsDao contactsDao();
}
