package com.example.zhuyuting.tablayout.Adapter;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.zhuyuting.tablayout.Entity.Coach;

import java.util.ArrayList;
import java.util.List;

public class SQLService {

    private DBOpenHelper dbOpenHelper;

    public SQLService(Context context) {
        this.dbOpenHelper = new DBOpenHelper(context);
    }

    public void save(Coach coach){
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",coach.getName());
        values.put("imageUrl",coach.getImageUrl());
        db.insert("coach_test", null, values);
    }

    //查询多个数据
    public List<Coach> getScrollData(){
        List<Coach> list = new ArrayList<Coach>();
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("coach_test", null, null,null, null, null, null, null);
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String imageUrl = cursor.getString(cursor.getColumnIndex("imageUrl"));
            Coach coach = new Coach(name,imageUrl);
            list.add(coach);
        }
        return list;
    }

    public void DeleteAll(){
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("delete from coach_test");
    }

}