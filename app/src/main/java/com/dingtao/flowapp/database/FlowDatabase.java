package com.dingtao.flowapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dingtao
 * @date 2018/11/30 15:32
 * qq:1940870847
 */
public class FlowDatabase extends SQLiteOpenHelper {

    SQLiteDatabase db;
    private final static String TABLE_NAME = "search";

    public FlowDatabase( Context context) {
        super(context, "flow.db", null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(_id integer primary key autoincrement,keys text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 插入搜索数据
     */
    public void insert(String keys){
        ContentValues values = new ContentValues();
        values.put("keys",keys);
        db.insert(TABLE_NAME,null,values);
    }


    /**
     * 删除搜索数据
     */
    public void delete(){
        db.delete(TABLE_NAME,null,null);
    }

    /**
     * 查询数据
     */
    public List<String> query(){
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        List<String> list = new ArrayList<>();
        while (cursor.moveToNext()){
            String keys = cursor.getString(cursor.getColumnIndex("keys"));
            list.add(keys);
        }
        return list;
    }

}
