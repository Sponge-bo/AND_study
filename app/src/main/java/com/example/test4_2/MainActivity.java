package com.example.test4_2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test4_2.activity.IndexActivity;
import com.example.test4_2.activity.RegisterActivity;
import com.example.test4_2.dataoperation.UserDatabase;

public class MainActivity extends Activity implements View.OnClickListener {
//    private String phone_str = "";
////    private String pad_str = "";
////    private String sex_str = "男";
////    private String hobby_str = "1";
////    private String city_str = "";

    private Button registerButton, loginButton;
    private EditText usernameText, paswdEdit;
    private EditText etUsername, etPassword;
    private CheckBox cbRemember;
    private SharedPreferences sp;
    private boolean isRemember;

//    EditText Username,Password;
//    RadioGroup sex_group;
//    RadioButton nan_but, nv_but;
//    CheckBox play, read, music;
//    Button register;
//    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.use_login);

        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        cbRemember = findViewById(R.id.remember_password);

        sp = getSharedPreferences("login", MODE_PRIVATE);

        // 从SharedPreferences中获取保存的用户名
        String savedUsername = sp.getString("username", "");
        isRemember = sp.getBoolean("isRemember", false);

        etUsername.setText(savedUsername);
        etUsername.setText(sp.getString("username", ""));

        if (isRemember) {
            etPassword.setText(sp.getString("password", ""));
            cbRemember.setChecked(true);
        }
        init();
//        phone_edit = (EditText)findViewById(R.id.reg_username);
//        pad_edit = (EditText)findViewById(R.id.reg_password);
//        sex_group = (RadioGroup)findViewById(R.id.sex);
//
//        sex_group.setOnCheckedChangeListener(this);
//        nan_but = (RadioButton)findViewById(R.id.reg_man);
//        nv_but = (RadioButton)findViewById(R.id.reg_woman);
//        read = (CheckBox) findViewById(R.id.read_book);
//        play = (CheckBox) findViewById(R.id.play_ball);
//        music = (CheckBox) findViewById(R.id.music);
//        register = (Button) findViewById(R.id.reg_register);
//
//        register.setOnClickListener(this);
//        spinner = (Spinner) findViewById(R.id.spinner);
//        final String[] city = new String[]{"嘉兴","东莞","杭州","桂平","广州","深圳","石龙","贵港"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,city);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                city_str = city[position];
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                /*phone_str = phone_edit.getText().toString();
                pad_str = pad_edit.getText().toString();
                hobby_str = "";
                if(read.isChecked()){
                    hobby_str += read.getText().toString();
                }if(play.isChecked()){
                    hobby_str += play.getText().toString();
                }if(music.isChecked()){
                    hobby_str += music.getText().toString();
                }
                Intent intent = new Intent(this,SecondActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("phone",phone_str);
                bundle.putString("pad",pad_str);
                bundle.putString("sex",sex_str);
                bundle.putString("hobby",hobby_str);
                bundle.putString("city",city_str);
                intent.putExtras(bundle);
                startActivity(intent);*/
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;

            case R.id.login:
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                //保存用户信息
                if (cbRemember.isChecked()) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.putBoolean("isRemember", true);
                    editor.apply();
                }else {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.remove("username");
                    editor.remove("password");
                    editor.putBoolean("isRemember", false);
                    editor.apply();
                }

                //登录逻辑
                if (validateLogin()) {
                    Intent intent1 = new Intent(MainActivity.this, IndexActivity.class);
                    Bundle bundle = new Bundle();
                    UserDatabase userDatabase = new UserDatabase(MainActivity.this);
                    bundle.putString("username", usernameText.getText().toString());
                    bundle = userDatabase.queryUserInfo(userDatabase.getReadableDatabase(), bundle);
                    intent1.putExtras(bundle);
                    startActivity(intent1);
                }else {
                    Toast.makeText(MainActivity.this, "账号或者密码错误", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
//    @Override
//    public void onCheckedChanged(RadioGroup group, @IdRes int i) {
//        sex_str = i == R.id.reg_man?"男性":"女性";
//    }
    //登录验证
    private void init() {
        usernameText = findViewById(R.id.username);
        paswdEdit = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(this);
        registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(this);
    }
    private boolean validateLogin() {
        String username = usernameText.getText().toString();
        String password = paswdEdit.getText().toString();
        UserDatabase userDatabase = new UserDatabase(MainActivity.this);
        SQLiteDatabase sqLiteDatabase = userDatabase.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from job_user where username=? and password=?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }
}