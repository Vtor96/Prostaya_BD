package com.example.prostoyspisok.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.prostoyspisok.database.UserDBSchema.*;


public class UserBaseHelper extends SQLiteOpenHelper{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "userBase.db";

    public UserBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
        Log.d("SYSTEM INFO: ", "Метод UserBaseHelper() запущен");
    }

    //если базы не существует, то вызывается метод onCreate
    @Override
    public void onCreate(SQLiteDatabase db){
        Log.d("SYSTEM INFO: ", "Метод onCreate() запущен");
        db.execSQL("create table " + UserTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                UserTable.Cols.UUID + "," +
                UserTable.Cols.FIRSTNAME + "," +
                UserTable.Cols.LASTNAME +"," +
                UserTable.Cols.PHONE + ")");
    }

    //если база существует и отличаются версии, то вызывается onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
}
