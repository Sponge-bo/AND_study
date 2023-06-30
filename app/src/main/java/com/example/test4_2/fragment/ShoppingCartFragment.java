package com.example.test4_2.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test4_2.R;
//import com.example.test4_2.adapter.MessageAdapter;
import com.example.test4_2.entity.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartFragment extends Fragment implements View.OnClickListener {
    private LinearLayout messageButton;
    private SendmessageFragment sendmessageFragment;
    private List<Message> messageList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view;
        view = LayoutInflater.from(getActivity()).inflate(R.layout.cart_no_product, container, false);
        init(view);
        return view;
    }



    private void init(View view) {
        messageButton = view.findViewById(R.id.message1);
        messageButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message1:
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                if (sendmessageFragment == null) {
                    sendmessageFragment = new SendmessageFragment();
                }
                transaction.replace(R.id.main_content,sendmessageFragment);
                transaction.commit();
                break;
        }
    }
}