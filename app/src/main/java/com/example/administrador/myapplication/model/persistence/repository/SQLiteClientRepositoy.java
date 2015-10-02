package com.example.administrador.myapplication.model.persistence.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.myapplication.model.entities.Client;
import com.example.administrador.myapplication.model.persistence.contract.ClientContract;
import com.example.administrador.myapplication.model.persistence.DatabaseHelper;
import com.example.administrador.myapplication.model.persistence.repository.interface_repository.ClientRepository;
import com.example.administrador.myapplication.util.AppUtil;

import java.util.List;

public class SQLiteClientRepositoy implements ClientRepository {

    private static SQLiteClientRepositoy singletonInstance;

    private SQLiteClientRepositoy() {
        super();
    }

    //pega a instacia do SQLite, que e um singleton
    public static ClientRepository getInstance() {
        if (SQLiteClientRepositoy.singletonInstance == null) {
            SQLiteClientRepositoy.singletonInstance = new SQLiteClientRepositoy();
        }
        return SQLiteClientRepositoy.singletonInstance;
    }

    @Override
    public void save(Client client) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT); //capturei meu db
        SQLiteDatabase db = helper.getReadableDatabase(); //obtive uma instacia do db

        //valores
        ContentValues values = ClientContract.getContentValues(client);

        if (client.getId() == null) {
            db.insert(ClientContract.TABLE_CLIENT, null, values);
        } else {
            String where = ClientContract.CLIENT_ID + " = ?";
            String[] args = {client.getId().toString()};
            db.update(ClientContract.TABLE_CLIENT, values, where, args);
        }
        db.close();
        helper.close();
    }

    @Override
    public List<Client> getAll() {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT); //capturei meu db
        SQLiteDatabase db = helper.getReadableDatabase(); //obtive uma instacia do db

        Cursor cursor = db.query(ClientContract.TABLE_CLIENT, ClientContract.COLUMNS, null, null, null, null, ClientContract.CLIENT_NAME);

        List<Client> clients = ClientContract.bindList(cursor);

        db.close();
        helper.close();

        return clients;
    }

    @Override
    public void delete(Client cliente) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT); //capturei meu db
        SQLiteDatabase db = helper.getReadableDatabase(); //obtive uma instacia do db

        String where = ClientContract.CLIENT_ID + " = ?";
        String[] args = {cliente.getId().toString()};

        db.delete(ClientContract.TABLE_CLIENT, where, args);

        db.close();
        helper.close();
    }
}
