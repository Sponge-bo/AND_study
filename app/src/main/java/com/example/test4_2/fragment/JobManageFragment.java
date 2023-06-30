package com.example.test4_2.fragment;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;

import com.example.test4_2.R;
import com.example.test4_2.dataoperation.JobDatabase;

import java.util.List;

public class JobManageFragment extends Fragment implements View.OnClickListener  {
    private ScrollView scrollView;
    private LinearLayout linearLayout;
    private JobInfoFragment jobInfoFragment;
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.job_manger, container, false);
        init(view);
        //在活动或片段中初始化数据库助手类
        JobDatabase databaseHelper = new JobDatabase(getActivity());
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        //从数据库中获取信息数据

        List<String> infoList = databaseHelper.getJobInfoData(db);

// 获取容器的引用
        LinearLayout containerLayout = view.findViewById(R.id.linear_layout);
        for (String info : infoList) {
            // 创建一个新的TextView
            TextView textView = new TextView(getActivity());
            textView.setText(info+ "\n" + "以下为兼职信息");
            textView.setTextSize(15); // 设置字体大小为 16sp

            int width = 100; // 设置宽度
            int height = 100; // 设置高度
            Drawable drawable = getResources().getDrawable(R.drawable.mes1); // 获取图像资源
            drawable.setBounds(0, 0, width, height);
            // 使用 setCompoundDrawablesWithIntrinsicBounds() 方法设置图像
            textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);

            textView.setBackgroundResource(R.drawable.textview_border);

            // 设置TextView的布局参数
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            // 设置其他布局参数属性（可选）
            layoutParams.gravity = Gravity.CENTER;

            layoutParams.width = 1400;
            layoutParams.height = 400;
            layoutParams.leftMargin = 16;
            layoutParams.topMargin = 50;
            layoutParams.rightMargin = 16;
            layoutParams.bottomMargin = 50;
            textView.setLayoutParams(layoutParams);

            // 将TextView添加到容器中
            containerLayout.addView(textView);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    if (jobInfoFragment == null) {
                        jobInfoFragment = new JobInfoFragment();
                    }
                    transaction.replace(R.id.main_content,jobInfoFragment);
                    transaction.commit();
                }
            });
        }
        return view;
    }

    private void init(View view) {
        scrollView = view.findViewById(R.id.scrollJob);
        linearLayout = view.findViewById(R.id.linear_layout);
    }
    @Override
    public void onClick(View v) {

    }
}
