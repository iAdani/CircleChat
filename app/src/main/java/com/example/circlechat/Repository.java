package com.example.circlechat;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.circlechat.Dao.ContactsDao;
import com.example.circlechat.api.ContactsWebService;
import com.example.circlechat.entities.Contact;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static String jwtToken = null;
    private static String firebaseToken = null;
    private static Database database = null;
    private ContactsDao contactsDao;
    private MutableLiveData<List<Contact>> contacts;
    private ContactsWebService contactsWebService;

    public Repository(MutableLiveData<List<Contact>> contacts) {
        this.contacts = contacts;

        // Setting values form the local DB
        if (database == null) {
            database = Room.databaseBuilder(CircleChatApp.getContext(), Database.class, "database")
                    .allowMainThreadQueries()
                    .build();
        }
        contactsDao = database.contactsDao();

        if (contacts == null) {
            contactsWebService = new ContactsWebService();
            return;
        }

        // If DB is empty make a new list
        List<Contact> response = contactsDao.GetAll();
        if (response == null) response = new ArrayList<>();
        this.contacts.setValue(response);

        // Fetching new data from the server
        contactsWebService = new ContactsWebService();
        contactsWebService.getContacts(this);
    }

    public static String getJwtToken() { return jwtToken; }

    public static void setJwtToken(String jwtToken) {
        Repository.jwtToken = jwtToken;
    }

    public void addContact(Contact contact, AddContactActivity context) {
        List<Contact> currentContacts = contactsDao.GetAll();
        for (Contact c : currentContacts) {
            if (c.getId().equals(contact.getId())) {
                Toast.makeText(context,
                        c.getId() + " Is already a contact!", Toast.LENGTH_SHORT).show();
                context.finish();
                return;
            }
        }

        // Insert to server
        contactsWebService.addContact(contact, context, this);
    }

    public static String getFirebaseToken() { return firebaseToken; }

    public static void setFirebaseToken(String firebaseToken) {
        Repository.firebaseToken = firebaseToken;
    }

    public MutableLiveData<List<Contact>> getContactsList() { return contacts; }

    public void updateContacts() {
        contactsDao.Clear();
        contactsDao.Insert(contacts.getValue());
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts.setValue(contacts);
    }
}
