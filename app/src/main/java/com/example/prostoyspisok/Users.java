package com.example.prostoyspisok;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.prostoyspisok.database.UserBaseHelper;
import com.example.prostoyspisok.database.UserDBSchema;

import java.util.ArrayList;
import java.util.List;

public class Users{
    private static Users users;
    private SQLiteDatabase database;
    private Context context;
    private List<User> userList;

    public static Users get(Context context){
        if(users == null){
            users = new Users(context);
        }
        return users;
    }

    private Users(Context context){
        this.context = context.getApplicationContext();
        this.database = new UserBaseHelper(context).getWritableDatabase();
    }

    public void addUser(User user){ //метод добавления пользователя в БД
        ContentValues values = getContentValues(user);
        database.insert(UserDBSchema.UserTable.NAME, null, values);
    }

    public void upgUser(User user) {
        String uuidString = user.getUuid().toString();
        ContentValues values = getContentValues(user);

        database.update(UserDBSchema.UserTable.NAME,values,
                UserDBSchema.UserTable.Cols.UUID+"=?", new String[]{uuidString});
    }

    public void deleteUser(String uuid) {
        String del = String.format("%s = '%s'", UserDBSchema.UserTable.Cols.UUID, uuid);
        database.delete(UserDBSchema.UserTable.NAME, del, null);
    }

    private static ContentValues getContentValues(User user){
        ContentValues values = new ContentValues();
        values.put(UserDBSchema.UserTable.Cols.UUID, user.getUuid().toString());
        values.put(UserDBSchema.UserTable.Cols.FIRSTNAME, user.getUserName());
        values.put(UserDBSchema.UserTable.Cols.LASTNAME, user.getUserLastName());
        values.put(UserDBSchema.UserTable.Cols.PHONE, user.getPhone());
        return values;
    }

    private UserCursorWrapper queryUsers(){
        Cursor cursor = database.query(UserDBSchema.UserTable.NAME, null,
                null, null, null, null, null);
        return new UserCursorWrapper(cursor);
    }

    public List<User> getUserList(){
        userList = new ArrayList<User>();
        UserCursorWrapper cursorWrapper = queryUsers();

        try{
            cursorWrapper.moveToFirst();
            while(!cursorWrapper.isAfterLast()){
                User user = cursorWrapper.getUser();
                userList.add(user);
                cursorWrapper.moveToNext();
            }
        }finally{
            cursorWrapper.close();
        }
        return this.userList;
    }
}