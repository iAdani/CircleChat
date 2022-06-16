package com.example.circlechat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.circlechat.databinding.ActivityChatListBinding;

import java.util.ArrayList;

public class ChatListActivity extends AppCompatActivity {

    // HARDCODED (!) DATA
    ArrayList<ContactContainerModel> contactContainerModels = new ArrayList<>();
    int[] profilePics = {R.drawable.contactcryingcat, R.drawable.contactcryingcat,
                        R.drawable.contactcryingcat, R.drawable.contactcryingcat, R.drawable.contactcryingcat,
                        R.drawable.contactcryingcat, R.drawable.contactcryingcat, R.drawable.contactcryingcat,
                        R.drawable.contactcryingcat, R.drawable.contactcryingcat};
    ActivityChatListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // for showing all the recycler views (contacts list)
        RecyclerView recyclerView = findViewById(R.id.myRecycler);
        setUpContactContainerModels();
        CC_RecyclerViewAdapter adapter = new CC_RecyclerViewAdapter(this, contactContainerModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.addNewChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatListActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });

        binding.settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatListActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    // has to do with the recycler view (HARDCODED!)
    private void setUpContactContainerModels() {
        String[] nicknames = getResources().getStringArray(R.array.nicknames);
        String[] lastMessages = getResources().getStringArray(R.array.lastMessage);
        String[] time = getResources().getStringArray(R.array.time);

        for (int i = 0; i < nicknames.length; i++) {
            contactContainerModels.add(new ContactContainerModel(profilePics[i], nicknames[i], lastMessages[i], time[i]));
        }
    }

}