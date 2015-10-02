package com.example.administrador.myapplication.model.persistence.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.myapplication.model.entities.ClientAddress;
import com.example.administrador.myapplication.model.persistence.DatabaseHelper;
import com.example.administrador.myapplication.model.persistence.contract.ClientAddressContract;
import com.example.administrador.myapplication.model.persistence.repository.interface_repository.ClientAddressRepository;
import com.example.administrador.myapplication.util.AppUtil;

import java.util.List;

public class SQLiteClientAddressRepository implements ClientAddressRepository {

    private static SQLiteClientAddressRepository singletonInstance;

    public SQLiteClientAddressRepository() {
        super();
    }

    public static SQLiteClientAddressRepository getInstance() {
        if (SQLiteClientAddressRepository.singletonInstance == null) {
            SQLiteClientAddressRepository.singletonInstance = new SQLiteClientAddressRepository();
        }
        return SQLiteClientAddressRepository.singletonInstance;
    }

    public Long save(ClientAddress address) {
        Long idAddress = Long.valueOf(0);
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();

        ContentValues values = ClientAddressContract.getContentValues(address);

        if (address.getId() == null){
          idAddress = db.insert(ClientAddressContract.TABLE, null, values);
        } else {
            String where = ClientAddressContract.ID + " = ?";
            String[] args = {address.getId().toString()};
            db.update(ClientAddressContract.TABLE, values, where, args);
        }

        db.close();
        helper.close();
        return idAddress;
    }

    public List<ClientAddress> getAll(){
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(ClientAddressContract.TABLE, ClientAddressContract.COLUMNS, null, null, null, null, ClientAddressContract.ID);

        List<ClientAddress> clientAddresses = ClientAddressContract.bindList(cursor);

        db.close();
        helper.close();

        return clientAddresses;
    }

}
