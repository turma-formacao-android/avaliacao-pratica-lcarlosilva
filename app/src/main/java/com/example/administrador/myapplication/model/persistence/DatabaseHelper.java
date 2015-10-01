package com.example.administrador.myapplication.model.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrador.myapplication.model.persistence.contract.ClientContract;
import com.example.administrador.myapplication.model.persistence.contract.UserContract;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String BANCO_DADOS = "MY_DATABASE";
    public static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DatabaseHelper.BANCO_DADOS, null, DatabaseHelper.VERSION);
    }

    //instalacao do APP
    //onde eh realizado a criacao do banco de dados
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ClientContract.getSqlCreateTableClient());
        db.execSQL(UserContract.getSqlCreateTableUser());
        db.execSQL(UserContract.sqlInsertUser());
    }

    //atualizacao do APP
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
