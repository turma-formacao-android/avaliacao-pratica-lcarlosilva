package com.example.administrador.myapplication.model.persistence.contract;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrador.myapplication.model.entities.Client;
import com.example.administrador.myapplication.model.entities.ClientAddress;

import java.util.ArrayList;
import java.util.List;

public class ClientAddressContract {

    public static final String TABLE = "AddressClient";
    public static final String ID = "id";
    public static final String CEP = "cep";
    public static final String TIPODELOGRADOURO = "tipoDeLogradouro";
    public static final String LOGRADOURO = "logradouro";
    public static final String BAIRRO = "bairro";
    public static final String NUMERO = "numero";
    public static final String COMPLEMENTO = "complemento";
    public static final String CIDADE = "cidade";
    public static final String ESTADO = "estado";

    public static final String[] COLUMNS = {ID, CEP, TIPODELOGRADOURO, LOGRADOURO, BAIRRO, NUMERO, COMPLEMENTO, CIDADE, ESTADO};

    public static String getSqlCreateTableAddressClient() {
        StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(CEP + " TEXT, ");
        sql.append(TIPODELOGRADOURO + " TEXT, ");
        sql.append(LOGRADOURO + " TEXT, ");
        sql.append(BAIRRO + " TEXT, ");
        sql.append(NUMERO + " TEXT, ");
        sql.append(COMPLEMENTO + " TEXT, ");
        sql.append(CIDADE + " TEXT, ");
        sql.append(ESTADO + " TEXT ");
        sql.append(" ); ");

        return sql.toString();
    }

    public static ContentValues getContentValues(ClientAddress clientAddress) {

        ContentValues values = new ContentValues();
        values.put(ClientAddressContract.ID, clientAddress.getId());
        values.put(ClientAddressContract.CEP, clientAddress.getCep());
        values.put(ClientAddressContract.TIPODELOGRADOURO, clientAddress.getTipoDeLogradouro());
        values.put(ClientAddressContract.LOGRADOURO, clientAddress.getLogradouro());
        values.put(ClientAddressContract.BAIRRO, clientAddress.getBairro());
        values.put(ClientAddressContract.NUMERO, clientAddress.getNumero());
        values.put(ClientAddressContract.COMPLEMENTO, clientAddress.getComplemento());
        values.put(ClientAddressContract.CIDADE, clientAddress.getCidade());
        values.put(ClientAddressContract.ESTADO, clientAddress.getEstado());

        return values;
    }

    public static ClientAddress bind(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Client client = new Client();
            ClientAddress address = new ClientAddress();
            client.setId(cursor.getLong(cursor.getColumnIndex(ClientContract.CLIENT_ID)));

            client.setAddress(address.getId());
            return address;
        }
        return null;
    }

    public static List<ClientAddress> bindList(Cursor cursor) {
        final List<ClientAddress> serviceOrders = new ArrayList<>();
        while (cursor.moveToNext()) {
            serviceOrders.add(bind(cursor));
        }
        return serviceOrders;
    }

}