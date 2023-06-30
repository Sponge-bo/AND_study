package com.example.test4_2.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.test4_2.R;

public class UserActivity extends Activity implements View.OnClickListener {
    private ImageView userIconImage;
    private TextView usernameText, userSexText, userCityText;
    private LinearLayout usernameLine, userSexline, userCityLine, userPayLine, userSettingLine, userGeneralLine, userSearchProductLine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_user);
       /* usernameText = findViewById(R.id.user_username);
        userSexText = findViewById(R.id.user_sex);
        userCityText = findViewById(R.id.user_city);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        String username = bundle.getString("username");
        String usersex = bundle.getString("sex");
        String city = bundle.getString("city");
        userSexText.setText("性别："+ usersex);
        usernameText.setText("用户名：" + username);
        userCityText.setText("城市："  + city);*/
        init();
    }
    private void init() {
        userIconImage = findViewById(R.id.user_icon);
        usernameLine = findViewById(R.id.user_username_line);
        userSexline = findViewById(R.id.user_sex_line);
        userCityLine = findViewById(R.id.user_city_line);
        userPayLine = findViewById(R.id.user_pay);
        userSettingLine = findViewById(R.id.user_setting);
        userGeneralLine = findViewById(R.id.user_general);
        userSearchProductLine = findViewById(R.id.user_searchProduct);
        userSearchProductLine.setOnClickListener(this);
        setData();
    }

    private void setData() {
        Intent intent = UserActivity.this.getIntent();
        Bundle bundle = intent.getExtras();
        usernameText.setText(String.format("用户名", bundle.getString("username")));
        userSexText.setText(String.format("性别", bundle.getString("sex")));
        userCityText.setText(String.format("城市", bundle.getString("city")));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_searchProduct:
                Intent intent1 = new Intent(UserActivity.this, CategoryActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
