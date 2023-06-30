package com.example.test4_2.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test4_2.MainActivity;
import com.example.test4_2.R;

public class PearsonFragment extends Fragment implements View.OnClickListener {
    private ImageView userIconImage;
    private TextView usernameText, userSexText, userCityText;
    private LinearLayout usernameLine, userSexline, userCityLine, userPayLine, userSettingLine, userGeneralLine,userSearchProduct;
    private MainActivity mainActivity;
    private SettingFragment settingFragment;
    private JobManageFragment jobManageFragment;
    private Job_order_Fragment job_order_fragment;
    private User_credit_Fragment user_credit_fragment;
    private Button walkButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.content_user, container, false);
        init(view);

        userSearchProduct.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                if (user_credit_fragment == null) {
                    user_credit_fragment = new User_credit_Fragment();
                }
                transaction.replace(R.id.main_content,user_credit_fragment);
                transaction.commit();
            }
        });

        userPayLine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                if (job_order_fragment == null) {
                    job_order_fragment = new Job_order_Fragment();
                }
                transaction.replace(R.id.main_content,job_order_fragment);
                transaction.commit();
            }
        });

        userGeneralLine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                if (jobManageFragment == null) {
                    jobManageFragment = new JobManageFragment();
                }
                transaction.replace(R.id.main_content,jobManageFragment);
                transaction.commit();
            }
        });

        userSettingLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                if (settingFragment == null) {
                    settingFragment = new SettingFragment();
                }
                transaction.replace(R.id.main_content,settingFragment);
                transaction.commit();
            }
        });

        return view;
    }


    private void init(View view) {
        userIconImage = view.findViewById(R.id.user_icon);
        usernameText = view.findViewById(R.id.user_username);
        userSexText = view.findViewById(R.id.user_sex);
        userCityText = view.findViewById(R.id.user_city);
        usernameLine = view.findViewById(R.id.user_username_line);
        userSexline = view.findViewById(R.id.user_sex_line);
        userCityLine = view.findViewById(R.id.user_city_line);
        userPayLine = view.findViewById(R.id.user_pay);
        userSettingLine = view.findViewById(R.id.user_setting);
        userGeneralLine = view.findViewById(R.id.user_general);
        userSearchProduct = view.findViewById(R.id.user_searchProduct);

        walkButton = view.findViewById(R.id.exit);
        walkButton.setOnClickListener(this);
        setData();
    }

    private void setData() {
        Bundle bundle = getArguments();
        usernameText.setText(String.format("用户名:\t\t%s", bundle.getString("username")));
        userSexText.setText(String.format("性    别:\t\t%s", bundle.getString("sex")));
        userCityText.setText(String.format("城    市:\t\t%s", bundle.getString("city")));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exit:
                // 创建Intent对象，指定目标Activity
                Intent intent = new Intent(getActivity(), MainActivity.class);
                // 启动Activity
                startActivity(intent);
                break;
        }
    }
}