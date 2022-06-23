package com.example.circlechat;

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
    private ContactsDao contactsDao;
    private MutableLiveData<List<Contact>> contacts;
    private ContactsWebService contactsWebService;

    public Repository() {
        contacts = new MutableLiveData<>();

        // Setting values form the local DB
        Database db = Room.databaseBuilder(CircleChatApp.getContext(), Database.class, "DB")
                .allowMainThreadQueries().build();
        contactsDao = db.contactsDao();

        // If DB is empty make a new list
        List<Contact> response = contactsDao.GetAll();
        if (response.size() == 0) response = new ArrayList<>();
        contacts.setValue(response);

        // Fetching new data from the server
        contactsWebService = new ContactsWebService();
        contactsWebService.getContacts(contacts);
    }


    public static String getJwtToken() { return jwtToken; }

    public static void setJwtToken(String jwtToken) {
        Repository.jwtToken = jwtToken;
    }

    public static String getFirebaseToken() { return firebaseToken; }

    public static void setFirebaseToken(String firebaseToken) {
        Repository.firebaseToken = firebaseToken;
    }

    public void addContact(Contact contact) {
        contactsDao.Insert(contact);
    }

    public MutableLiveData<List<Contact>> getContactsList() { return contacts; }

    public void updateContacts() {
        contactsDao.Update(contacts.getValue());
    }
//    public MutableLiveData<List<Contact>> getContactsList() {
//        return contactsLiveData;
//    }
//
//    class ContactsLiveData extends MutableLiveData<List<Contact>> {
//        public ContactsLiveData() {
//            super();
//            contacts.setValue(contactsDao.GetAll());
//        }
//    }
}
