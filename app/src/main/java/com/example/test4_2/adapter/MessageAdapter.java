//package com.example.test4_2.adapter;
//
//import android.os.Message;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.test4_2.R;
//
//import java.util.List;
//
//public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
//    private List<Message> messageList;
//
//    public MessageAdapter(List<Message> messageList) {
//        this.messageList = messageList;
//    }
//
//    @Override
//    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sent_messege, parent, false);
//        return new MessageViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(MessageViewHolder holder, int position) {
//        Message message = messageList.get(position);
//        holder.bind(message);
//    }
//
//    @Override
//    public int getItemCount() {
//        return messageList.size();
//    }
//
//    public class MessageViewHolder extends RecyclerView.ViewHolder {
//        private TextView messageTextView;
//
//        public MessageViewHolder(View itemView) {
//            super(itemView);
//            messageTextView = itemView.findViewById(R.id.messageTextView);
//        }
//
//        public void bind(Message message) {
//            messageTextView.setText(message.getText());
//    }
//    }
//}
