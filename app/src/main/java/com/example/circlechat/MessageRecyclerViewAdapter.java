package com.example.circlechat;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.circlechat.entities.Contact;
import com.example.circlechat.entities.Message;

public class MessageRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final Context context;
    List<Message> list;
    public static final int MESSAGE_TYPE_IN = 1;
    public static final int MESSAGE_TYPE_OUT = 2;
    Contact currentContact;

    public MessageRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMessages(List<Message> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    private class MessageInViewHolder extends RecyclerView.ViewHolder {

        TextView messageTV;
        MessageInViewHolder(final View itemView) {
            super(itemView);
            messageTV = itemView.findViewById(R.id.textMessage);
        }
        void bind(int position) {
            MessageModel messageModel = new MessageModel(list.get(position).getContent(), 1);
            messageTV.setText(messageModel.message);
        }
    }

    private class MessageOutViewHolder extends RecyclerView.ViewHolder {

        TextView messageTV;
        MessageOutViewHolder(final View itemView) {
            super(itemView);
            messageTV = itemView.findViewById(R.id.textMessage);
        }
        void bind(int position) {
            MessageModel messageModel = new MessageModel(list.get(position).getContent(), 2);
            messageTV.setText(messageModel.message);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MESSAGE_TYPE_IN) {
            return new MessageInViewHolder(LayoutInflater.from(context).inflate(R.layout.item_container_recieved_message, parent, false));
        }
        return new MessageOutViewHolder(LayoutInflater.from(context).inflate(R.layout.item_container_sent_message, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (currentContact != null && list.get(position).getBelongs().equals(currentContact.getId())) {
//            ((MessageOutViewHolder) holder).bind(position);
            ((MessageOutViewHolder) holder).bind(position);
        } else {
            ((MessageInViewHolder) holder).bind(position);
        }
    }

    @Override
    public int getItemCount() {
        if(list == null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
//        return list.get(position).messageType;
        if(currentContact == null) return 1;
        if (list.get(position).getBelongs().equals(currentContact.getId())) {
            return MESSAGE_TYPE_OUT;
        }
        return MESSAGE_TYPE_IN;
    }
}
