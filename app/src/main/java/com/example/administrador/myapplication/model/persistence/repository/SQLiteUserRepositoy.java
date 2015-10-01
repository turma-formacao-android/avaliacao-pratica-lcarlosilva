package com.example.administrador.myapplication.model.persistence.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.myapplication.model.entities.User;
import com.example.administrador.myapplication.model.persistence.DatabaseHelper;
import com.example.administrador.myapplication.model.persistence.contract.ClientContract;
import com.example.administrador.myapplication.model.persistence.contract.UserContract;
import com.example.administrador.myapplication.util.AppUtil;

import java.util.List;

public class SQLiteUserRepositoy implements UserRepository {

    private static SQLiteUserRepositoy singletonInstance;

    private SQLiteUserRepositoy() {
        super();
    }

    //pega a instacia do SQLite, que e um singleton
    public static SQLiteUserRepositoy getInstance() {
        if (SQLiteUserRepositoy.singletonInstance == null) {
            SQLiteUserRepositoy.singletonInstance = new SQLiteUserRepositoy();
        }
        return SQLiteUserRepositoy.singletonInstance;
    }

    @Override
    public void save(User user) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT); //capturei meu db
        SQLiteDatabase db = helper.getReadableDatabase(); //obtive uma instacia do db

        //valores
        ContentValues values = UserContract.getContentValues(user);

        if (user.getIdUser() == null) {
            db.insert(ClientContract.TABLE_CLIENT, null, values);
        } else {
            String where = ClientContract.CLIENT_ID + " = ?";
            String[] args = {user.getIdUser().toString()};
            db.update(ClientContract.TABLE_CLIENT, values, where, args);
        }
        db.close();
        helper.close();
    }

    @Override
    public List<User> getAll() {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT); //capturei meu db
        SQLiteDatabase db = helper.getReadableDatabase(); //obtive uma instacia do db

        Cursor cursor = db.query(UserContract.TABLE_USER, UserContract.COLUMNS, null, null, null, null, UserContract.USER_NAME);

        List<User> user = UserContract.bindList(cursor);

        db.close();
        helper.close();

        return user;
    }

    @Override
    public void delete(User user) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT); //capturei meu db
        SQLiteDatabase db = helper.getReadableDatabase(); //obtive uma instacia do db

        String where = ClientContract.CLIENT_ID + " = ?";
        String[] args = {user.getIdUser().toString()};

        db.delete(ClientContract.TABLE_CLIENT, where, args);

        db.close();
        helper.close();
    }
}
