package com.example.circlechat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.circlechat.databinding.ActivityAddContactBinding;
import com.example.circlechat.entities.Contact;

public class AddContactActivity extends AppCompatActivity {
    ActivityAddContactBinding binding;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = new Repository(null);

        binding.backIcon.setOnClickListener(v -> finish());

        binding.addContactAddButton.setOnClickListener(v -> {
            Contact contact = new Contact(binding.addContactUsername.getText().toString(),
                                            binding.addContactServer.getText().toString(),
                                            binding.addContactNickname.getText().toString(),
                                            null, null);
            repository.addContact(contact, this);
        });
    }
}