package com.example.test4_2.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test4_2.R;

public class SendmessageFragment extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.sent_messege, container, false);
        //        // 在Activity或Fragment中获取RecyclerView实例
//        RecyclerView messageRecyclerView = view.findViewById(R.id.messageRecyclerView);
//
//        // 设置布局管理器
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        messageRecyclerView.setLayoutManager(layoutManager);
//
//        // 创建适配器并设置给RecyclerView
//        MessageAdapter messageAdapter = new MessageAdapter(messageList); // 这里的messageList是你的消息数据集合
//        messageRecyclerView.setAdapter(messageAdapter);
//
//        // 获取输入框和发送按钮实例
//        EditText messageEditText = view.findViewById(R.id.messageEditText);
//        Button sendButton = view.findViewById(R.id.sendButton);
//        // 设置发送按钮的点击事件
//        sendButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String messageText = messageEditText.getText().toString();
//                // 在这里处理发送消息的逻辑
//                // 可以将新消息添加到messageList中，并通知适配器刷新界面
//                messageList.add(new Message(messageText, true));
//                messageAdapter.notifyItemInserted(messageList.size() - 1);
//                messageEditText.setText("");
//            }
//        });
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
