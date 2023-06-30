package com.example.test4_2.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test4_2.R;
import com.example.test4_2.dataoperation.JobDatabase;
import com.example.test4_2.dataoperation.UserDatabase;

public class AddJobActivity extends AppCompatActivity implements View.OnClickListener ,CheckBox.OnCheckedChangeListener{
    private Button submitButton ,returnButton;
    private EditText etusername,etpassword,ettime,etsalary,etlocation,etrequirement;
    private CheckBox etJobType1,etJobType2,etJobType3;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_job);
        init();
    }

    private void init() {
        submitButton = findViewById(R.id.submit_area);
        submitButton.setOnClickListener(this);
        returnButton = findViewById(R.id.return1);
        returnButton.setOnClickListener(this);
        etusername = findViewById(R.id.job_username);
        etpassword = findViewById(R.id.job_password);
        ettime = findViewById(R.id.job_time);
        etsalary= findViewById(R.id.job_salary);
        etlocation = findViewById(R.id.job_location);
        etrequirement = findViewById(R.id.job_requirement);
        imageView = findViewById(R.id.job_imageView);
        etJobType1 = findViewById(R.id.checkbox1);
        etJobType2 = findViewById(R.id.checkbox2);
        etJobType3 = findViewById(R.id.checkbox3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_area:
                if(validateUser()){
                    validateJobInfo();
                }else{
                    Toast.makeText(AddJobActivity.this, "验证失败(请检查用户名和密码！)", Toast.LENGTH_SHORT).show();
                }
            case R.id.return1:
                Intent intent = new Intent(AddJobActivity.this,IndexActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void validateJobInfo(){
        ettime = (EditText) findViewById(R.id.job_time);
        etsalary = (EditText)findViewById(R.id.job_salary);
        etlocation = (EditText)findViewById(R.id.job_location);
        etrequirement = (EditText)findViewById(R.id.job_requirement);
        etusername  = (EditText)findViewById(R.id.job_username);
        etpassword = (EditText)findViewById(R.id.job_password);
        etJobType1 = (CheckBox)findViewById(R.id.checkbox1);
        etJobType2 = (CheckBox)findViewById(R.id.checkbox2);
        etJobType3 = (CheckBox)findViewById(R.id.checkbox3);
        String type = "";

        if (etJobType1.isChecked()) {
            type += "志愿活动, ";
        }
        if (etJobType2.isChecked()) {
            type += "校内兼职, ";
        }
        if (etJobType3.isChecked()) {
            type += "校外兼职, ";
        }
        // 删除最后一个逗号和空格
        if (type.length() > 0) {
            type = type.substring(0, type.length() - 2);
        }

        String username = etusername.getText().toString().trim();
        String password = etpassword.getText().toString().trim();
        String time = ettime.getText().toString().trim();
        String salary = etsalary.getText().toString().trim();
        String location = etlocation.getText().toString().trim();
        String requirement = etrequirement.getText().toString().trim();

        if(!time.equals("") && !salary.equals("") && !location.equals("") && !requirement.equals("")){
            JobDatabase jobDatabase = new JobDatabase(AddJobActivity.this);
            SQLiteDatabase sqLiteDatabase = jobDatabase.getWritableDatabase();
            //创建数据库的表
//            jobDatabase.onCreate(sqLiteDatabase);
            // 插入数据
            Bundle bundle = new Bundle();
            bundle.putString("job_username",username);
            bundle.putString("job_password",password);
            bundle.putString("job_time",time);
            bundle.putString("job_salary",salary);
            bundle.putString("job_location",location);
            bundle.putString("job_requirement",requirement);
            bundle.putString("job_type",type);
            jobDatabase.insertJobData(sqLiteDatabase, bundle);
            Toast.makeText(AddJobActivity.this, "发布成功！", Toast.LENGTH_SHORT).show();
            //跳转到主页
//            FragmentManager fragmentManager = getSupportFragmentManager(); // 获取FragmentManager实例
//            IndexFragment myFragment = new IndexFragment(); // 创建Fragment实例
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction(); // 创建FragmentTransaction实例
//            fragmentTransaction.replace(R.id.container, myFragment); // 将Fragment添加到Activity中
//            fragmentTransaction.commit(); // 提交事务

//            Intent intent = new Intent(AddJobActivity.this, IndexActivity.class);
//            intent.putExtras(bundle);
//            startActivity(intent);
        }else{
            Toast.makeText(AddJobActivity.this, "请把信息填写完整！", Toast.LENGTH_SHORT).show();
        }

    }

    //验证用户信息
    private boolean validateUser() {
        String username = etusername.getText().toString();
        String password = etpassword.getText().toString();
        UserDatabase userDatabase = new UserDatabase(this);
        SQLiteDatabase sqLiteDatabase = userDatabase.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from job_user where username=? and password=?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    @Override//多选操作
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            // 当多选按钮被选中时的操作

        } else {
            // 当多选按钮被取消选中时的操作

        }
    }

    //选择图片
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