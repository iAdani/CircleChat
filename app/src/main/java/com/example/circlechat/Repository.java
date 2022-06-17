package com.example.circlechat;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.circlechat.Dao.ContactsDao;
import com.example.circlechat.entities.Contact;

import java.util.List;

public class Repository {
    private static String jwtToken = null;
    private ContactsDao contactsDao;
    private MutableLiveData<List<Contact>> contacts;

    public Repository() {
        Database db = Room.databaseBuilder(CircleChatApp.getContext(), Database.class, "DB").build();
        contactsDao = db.contactsDao();
    }

    public static String getJwtToken() { return jwtToken; }

    public static void setJwtToken(String jwtToken) {
        Repository.jwtToken = jwtToken;
    }

    public void addContact(Contact contact) {
        contactsDao.Insert(contact);

    }

    // @TODO finish
    public MutableLiveData<List<Contact>> getContactsList() {
        return contacts;

    }
}
