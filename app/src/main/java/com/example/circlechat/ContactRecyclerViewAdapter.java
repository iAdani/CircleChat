package com.example.circlechat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.circlechat.entities.Contact;

import java.util.ArrayList;
import java.util.List;

// For showing all the contacts list!!!
public class ContactRecyclerViewAdapter extends RecyclerView.Adapter<ContactRecyclerViewAdapter.MyViewHolder> {
    private final Context context;
    private List<Contact> contacts;

    public ContactRecyclerViewAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ContactRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // this is where you inflate the layout (giving a look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_container_user, parent, false);
        return new ContactRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactRecyclerViewAdapter.MyViewHolder holder, int position) {
        // assigning values to the views we created in the recycler_view_row layout file
        // based on the position of the recycler view
        holder.tvNickname.setText(contacts.get(position).getName());
        holder.tvLastMessage.setText(contacts.get(position).getLast());
        holder.tvTime.setText(contacts.get(position).getLastdate());
        holder.imageView.setImageResource(R.drawable.contactcryingcat);

        holder.itemView.setOnClickListener(v -> {
            // when the user clicks on a row, we want to go to the chat activity
            // and pass the contact to it
            ChatActivity.setCurrentContact(contacts.get(position));
            Intent intent = new Intent(context, ChatActivity.class);
            context.startActivity(intent);
        });
    }

        @Override
    public int getItemCount() {
        // the recycler view just wants to know the number of items you want displayed
        return contacts.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // grabbing the views from our recycler_view_row layout file
        // kinda like in the onCreate method
        ImageView imageView;
        TextView tvNickname, tvLastMessage, tvTime;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_contact);
            tvNickname = itemView.findViewById(R.id.nickname);
            tvLastMessage = itemView.findViewById(R.id.lastMessage);
            tvTime = itemView.findViewById(R.id.time);
        }
    }
}
