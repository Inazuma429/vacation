package com.example.vacation;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//继承SQLiteOpenHelper，用于创建数据库
public class DBUser extends SQLiteOpenHelper {
    public DBUser(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    //重写onCreate方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        //该数据库创建user，message两个表
        String sql="create table user(id integer primary key autoincrement,username varchar(20),password varchar(20))";
        String sql2="create table message(mid integer primary key autoincrement,musername varchar(20),name varchar(20),money varchar(20),date varchar(20),inout varchar(20))";
        db.execSQL(sql);
        db.execSQL(sql2);
    }

    //此方法用于注册账号时将账号密码录入user表
    public void addData(SQLiteDatabase sd,String username,String password){
        ContentValues values=new ContentValues();
        values.put("username",username);
        values.put("password",password);
        sd.insert("user",null,values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists message");
        onCreate(db);
    }
}
