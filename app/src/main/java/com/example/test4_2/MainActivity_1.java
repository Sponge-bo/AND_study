package com.example.test4_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test4_2.activity.RegisterActivity;


public class MainActivity_1 extends AppCompatActivity implements View.OnClickListener{
    private Button registerButton, loginButton;
    private EditText usernameText, paswdEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.use_login);
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                Intent intent = new Intent(MainActivity_1.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login:
                break;
        }
    }

    private void init() {
        usernameText = findViewById(R.id.username);
        paswdEdit = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(this);
        registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(this);
    }
}
