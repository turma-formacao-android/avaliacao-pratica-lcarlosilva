package com.example.administrador.myapplication.model.persistence.contract;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrador.myapplication.model.entities.Client;
import com.example.administrador.myapplication.model.entities.ClientAddress;

import java.util.ArrayList;
import java.util.List;

public class ClientContract {

    public static final String TABLE_CLIENT = "Client";
    public static final String CLIENT_ID = "id";
    public static final String CLIENT_NAME = "name";
    public static final String CLIENT_AGE = "age";
    public static final String CLIENT_ADDRESS = "address";
    public static final String CLIENT_PHONE = "phone";

    public static final String[] COLUMNS = {CLIENT_ID, CLIENT_NAME, CLIENT_AGE, CLIENT_ADDRESS, CLIENT_PHONE};

    public static String getSqlCreateTableClient() {
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE_CLIENT);
        sql.append(" ( ");
        sql.append(CLIENT_ID + " INTEGER PRIMARY KEY, ");
        sql.append(CLIENT_NAME + " TEXT, ");
        sql.append(CLIENT_AGE + " TEXT, ");
        sql.append(CLIENT_ADDRESS + " INTEGER, ");
        sql.append(CLIENT_PHONE + " TEXT ");
        sql.append(" ); ");

        return sql.toString();
    }

    public static ContentValues getContentValues(Client client) {
        ContentValues values = new ContentValues();
        values.put(ClientContract.CLIENT_ID, client.getId());
        values.put(ClientContract.CLIENT_NAME, client.getName());
        values.put(ClientContract.CLIENT_AGE, client.getAge());
        values.put(ClientContract.CLIENT_PHONE, client.getPhone());
        values.put(ClientContract.CLIENT_ADDRESS, client.getAddress());
        return values;
    }

    public static Client bind(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Client client = new Client();
            ClientAddress address = new ClientAddress();
            client.setId(cursor.getLong(cursor.getColumnIndex(ClientContract.CLIENT_ID)));
            client.setName(cursor.getString(cursor.getColumnIndex(ClientContract.CLIENT_NAME)));
            client.setAge(cursor.getInt(cursor.getColumnIndex(ClientContract.CLIENT_AGE)));
            client.setPhone(cursor.getString(cursor.getColumnIndex(ClientContract.CLIENT_PHONE)));

            address.setId(cursor.getLong(cursor.getColumnIndex(ClientContract.CLIENT_ADDRESS)));

            client.setAddress(address.getId());
            return client;
        }
        return null;
    }

    public static List<Client> bindList(Cursor cursor) {
        final List<Client> serviceOrders = new ArrayList<>();
        while (cursor.moveToNext()) {
            serviceOrders.add(bind(cursor));
        }
        return serviceOrders;
    }

}