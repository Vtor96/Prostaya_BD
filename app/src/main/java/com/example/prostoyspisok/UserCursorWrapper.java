package com.example.prostoyspisok;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.prostoyspisok.database.UserDBSchema;

import java.util.UUID;

public class UserCursorWrapper extends CursorWrapper{

    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public UserCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public User getUser(){
        String uuidString = getString(getColumnIndex(UserDBSchema.UserTable.Cols.UUID));
        String userName = getString(getColumnIndex(UserDBSchema.UserTable.Cols.FIRSTNAME));
        String userLastName = getString(getColumnIndex(UserDBSchema.UserTable.Cols.LASTNAME));
        String phone = getString(getColumnIndex(UserDBSchema.UserTable.Cols.PHONE));

        User user = new User(UUID.fromString(uuidString));
        user.setUserName(userName);
        user.setUserLastName(userLastName);
        user.setPhone(phone);

        return user;
    }
}
