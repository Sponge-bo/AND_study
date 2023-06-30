package com.example.test4_2.dataoperation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

public class UserDatabase extends SQLiteOpenHelper {


    public UserDatabase(@Nullable Context context) {
        super(context, "user", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table job_user(id integer primary key autoincrement, username varchar(50), password varchar(50),sex varchar(10),city carchar(50))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

    //插入数据
    public void insertUserData(SQLiteDatabase sqLiteDatabase, Bundle bundle) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", bundle.getString("username"));
        contentValues.put("password", bundle.getString("password"));
        contentValues.put("sex", bundle.getString("sex"));
        contentValues.put("city", bundle.getString("city"));
        sqLiteDatabase.insert("job_user", null, contentValues);
        sqLiteDatabase.close();
    }

    public Bundle queryUserInfo(SQLiteDatabase sqLiteDatabase, Bundle bundle) {
        String username = bundle.getString("username");
        Cursor cursor = sqLiteDatabase.rawQuery("select * from job_user where username=?", new String[]{username});
        if (cursor != null) {
            while (cursor.moveToNext()) {
                bundle.putString("sex", cursor.getString(3));
                bundle.putString("city", cursor.getString(4));
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return bundle;
    }

}

