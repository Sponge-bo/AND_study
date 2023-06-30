package com.example.test4_2.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test4_2.R;

import static android.app.Activity.RESULT_OK;

public class User_credit_Fragment extends Fragment implements View.OnClickListener {
    private ImageView imageView;
    private Button button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.user_credit, container, false);
        init(view);
        // 在 onCreateView 方法中获取按钮并设置点击监听器
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


        return view;
    }

    private void init(View view){
        button = view.findViewById(R.id.buttonSelectImage);
        imageView = view.findViewById(R.id.imageViewPhoto);

    }

    @Override
    public void onClick(View v) {

    }
    //选择图片
    public void selectImage() {
        // 创建一个Intent，打开系统的图库界面
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    //显示到ImageView上
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // 获取用户选择的头像URI
            Uri imageUri = data.getData();
            // 将头像显示到ImageView中
            imageView.setImageURI(imageUri);
        }
    }
}