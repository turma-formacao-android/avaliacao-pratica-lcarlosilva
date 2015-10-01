package com.example.administrador.myapplication.model.persistence.contract;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrador.myapplication.model.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserContract {

    //dados Login
    public static final String TABLE_USER = "user";
    public static final String USER_ID = "id";
    public static final String USER_NAME = "name";
    public static final String USER_PASSWORD = "password";

    public static final String[] COLUMNS = {USER_ID, USER_NAME, USER_PASSWORD};

    public static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(UserContract.USER_ID, user.getIdUser());
        values.put(UserContract.USER_NAME, user.getNameUser());
        values.put(UserContract.USER_PASSWORD, user.getPasswordUser());
        return values;
    }

    public static User bind(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            User user = new User();
            user.setIdUser(cursor.getLong(cursor.getColumnIndex(UserContract.USER_ID)));
            user.setNameUser(cursor.getString(cursor.getColumnIndex(UserContract.USER_NAME)));
            user.setPasswordUser(cursor.getString(cursor.getColumnIndex(UserContract.USER_PASSWORD)));
            return user;
        }
        return null;
    }

    public static List<User> bindList(Cursor cursor) {
        final List<User> serviceOrders = new ArrayList<>();
        while (cursor.moveToNext()) {
            serviceOrders.add(bind(cursor));
        }
        return serviceOrders;
    }

    public static String getSqlCreateTableUser() {

        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE_USER);
        sql.append(" ( ");
        sql.append(USER_ID + " INTEGER PRIMARY KEY, ");
        sql.append(USER_NAME + " TEXT, ");
        sql.append(USER_PASSWORD + " TEXT ");
        sql.append(" ); ");

        return sql.toString();
    }

    public static String sqlInsertUser(){
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO ");
        sql.append(TABLE_USER);
        sql.append(" ( ");
        sql.append(USER_NAME + ", ");
        sql.append(USER_PASSWORD + " ");
        sql.append(" ) ");
        sql.append(" VALUES ");
        sql.append("('admin', 'admin' )");

        return sql.toString();
    }
}