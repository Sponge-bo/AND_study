package com.example.test4_2.activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test4_2.dataoperation.UserDatabase;
import com.example.test4_2.R;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private TextView cityText;
    private EditText phone_edit,pad_edit,surpad_edit;
    private RadioGroup sex_group;
    private RadioButton nan_but, nv_but;
    private String sex_str;
    private ImageView imageView;
    Button register;
    CityPickerView cityPicker = new CityPickerView();

  @Override
      protected void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.register);
          init();
    }
    private void init(){
        cityText = findViewById(R.id.reg_province);
        cityText.setOnClickListener(this);
        pad_edit = findViewById(R.id.reg_username);
        pad_edit = findViewById(R.id.reg_password);
        surpad_edit = findViewById(R.id.reg_sure_password);
        register = findViewById(R.id.reg_register);
        register.setOnClickListener(this);
        sex_group = findViewById(R.id.sex);
        sex_group.setOnCheckedChangeListener(this);
        imageView = findViewById(R.id.user_imageView);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reg_province:
                cityPicker.init(this);
                showCityPicker();
                break;
            case R.id.reg_register:
                validateRegister();
                break;
        }
   }

    private void showCityPicker() {
        CityConfig cityConfig = new CityConfig.Builder().build();
        cityPicker.setConfig(cityConfig);
        cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                Toast.makeText(RegisterActivity.this,province+" - "+city+" - "+district,Toast.LENGTH_LONG).show();
                cityText.setText(province+" - "+city+" - "+district);
            }
            @Override
            //点击取消
            public void onCancel(){
            }
        });
        cityPicker.showCityPicker();
    }

    private void validateRegister() {
        phone_edit = (EditText)findViewById(R.id.reg_username);
        pad_edit = (EditText)findViewById(R.id.reg_password);
        surpad_edit = (EditText) findViewById(R.id.reg_sure_password);
        sex_group = (RadioGroup)findViewById(R.id.sex);
        sex_group.setOnCheckedChangeListener(this);
        register = (Button) findViewById(R.id.reg_register);
        register.setOnClickListener(this);

        String username = phone_edit.getText().toString().trim();
        String password = pad_edit.getText().toString().trim();
        String surepassword = surpad_edit.getText().toString().trim();
        String city = cityText.getText().toString();
        if (username.length() > 10) {
            // 用户名不是10位
            Toast.makeText(this, "用户名不超过10位", Toast.LENGTH_SHORT).show();
        } else if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$")) {
            // 密码不包含大小写字母或者不是6-16位
            Toast.makeText(this, "密码必须包含大小写字母以及数字，且是6位-16位", Toast.LENGTH_SHORT).show();
        }
        else {
            // 用户名和密码都符合要求，可以进行后续操作
            if(password.equals(surepassword)){
                if(!username.equals("") && !password.equals("")){
                    if(!city.equals("")){
                        UserDatabase userDatabase = new UserDatabase(RegisterActivity.this);
                        SQLiteDatabase sqLiteDatabase = userDatabase.getWritableDatabase();
                        //创建数据库的表
//                      userDatabase.onCreate(sqLiteDatabase);
                        // 从数据库中获取所有已经注册的用户名
                        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM job_user WHERE username=?", new String[]{username});
//                       List<String> usernames = new ArrayList<>();
//                       while (cursor.moveToNext()) {
//                          String username1 = cursor.getString(cursor.getColumnIndex("username"));
//                          usernames.add(username1);
//                      }
//                      cursor.close();
                        if(cursor.moveToFirst()){
                            Toast.makeText(RegisterActivity.this, "该用户名已经被注册", Toast.LENGTH_SHORT).show();
                            cursor.close();
                        }else{
                            Bundle bundle = new Bundle();
                            bundle.putString("username", username);
                            bundle.putString("password", password);
                            bundle.putString("sex", sex_str);
                            bundle.putString("city",city);
                            userDatabase.insertUserData(sqLiteDatabase, bundle);
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, IndexActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this,"请选择地址",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this,"账号或密码未填写",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(RegisterActivity.this,"两次密码输入不一致",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int i) {
        sex_str = i == R.id.reg_man?"男性":"女性";
    }

    //用户头像选择
    public void selectImage(View view) {
        // 创建一个Intent，打开系统的图库界面
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }
    @Override
    //显示到ImageView上
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // 获取用户选择的头像URI
            Uri imageUri = data.getData();
            // 将头像显示到ImageView中
            imageView.setImageURI(imageUri);
        }
    }

}