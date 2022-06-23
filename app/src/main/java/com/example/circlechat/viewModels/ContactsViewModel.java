package com.example.circlechat.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.circlechat.Repository;
import com.example.circlechat.entities.Contact;

import java.util.List;

public class ContactsViewModel extends ViewModel {

    private MutableLiveData<List<Contact>> contacts;
    private Repository repository;

    public ContactsViewModel() {
        contacts = new MutableLiveData<>();
        repository = new Repository(contacts);
        contacts = repository.getContactsList();
    }

    public MutableLiveData<List<Contact>>
    get() {
        return contacts;
    }

    public void updateContactsDB() {
        repository.updateContacts();
    }
}
