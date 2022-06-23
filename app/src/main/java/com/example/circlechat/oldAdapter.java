//package com.example.circlechat;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.circlechat.entities.Message;
//
//import java.util.List;
//
//public class MessageRecyclerViewAdapter extends RecyclerView.Adapter<ContactRecyclerViewAdapter.MyViewHolder> {
//    private Context context;
//    private List<Message> messages;
//
//    public MessageRecyclerViewAdapter(Context context) {
//        this.context = context;
//    }
//
//    public void setMessages(List<Message> messages) {
//        this.messages = messages;
//        notifyDataSetChanged();
//    }
//
//    @NonNull
//    @Override
//    public ContactRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//// this is where you inflate the layout (giving a look to our rows)
////        LayoutInflater inflater = LayoutInflater.from(context);
////        View view = inflater.inflate(R.layout.item_container_message, parent, false);
////        return new MessageRecyclerViewAdapter.MyViewHolder(view);
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ContactRecyclerViewAdapter.MyViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//        }
//    }
//}
