package com.example.test4_2.dataoperation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class JobDatabase extends SQLiteOpenHelper {


    public JobDatabase(Context context) {
        super(context, "jobs_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table job_info"  + " (" +
                "id integer primary key autoincrement," +
                "username varchar(50), " +
                "password varchar(50)," +
                "time varchar(10)," +
                "salary varchar(50)," +
                "location varchar(50)," +
                "requirement varchar(50)," +
                "type varchar(50)" + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

    //插入数据
    public void insertJobData(SQLiteDatabase sqLiteDatabase, Bundle bundle) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", bundle.getString("job_username"));
        contentValues.put("password", bundle.getString("job_password"));
        contentValues.put("time", bundle.getString("job_time"));
        contentValues.put("salary", bundle.getString("job_salary"));
        contentValues.put("location", bundle.getString("job_location"));
        contentValues.put("requirement", bundle.getString("job_requirement"));
        contentValues.put("type",bundle.getString("job_type"));
        sqLiteDatabase.insert("job_info", null, contentValues);
        sqLiteDatabase.close();
    }

    public List<String> getJobInfoData(SQLiteDatabase sqLiteDatabase) {
        List<String> infoList = new ArrayList<>();

        // 构建查询语句
        String query = "SELECT * FROM job_info";

        // 执行查询语句并获取结果集
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        // 遍历结果集，将数据添加到列表中
        while (cursor.moveToNext()) {
            String info = cursor.getString(cursor.getColumnIndex("username"))+"     "+cursor.getString(cursor.getColumnIndex("time"));
//            String info1 = cursor.getString(cursor.getColumnIndex("time"));
            infoList.add(info);
//            infoList.add(info1);
        }
        // 关闭结果集和游标
        cursor.close();
        return infoList;
    }

}
