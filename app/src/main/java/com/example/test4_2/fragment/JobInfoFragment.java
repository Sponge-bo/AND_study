package com.example.test4_2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;

import com.example.test4_2.R;

public class JobInfoFragment extends Fragment {
    // 构造方法
    public JobInfoFragment() {
        // 空的构造方法
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 加载Fragment的布局文件
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        // 在此进行布局文件中控件的初始化和其他操作
        return view;
    }
}
