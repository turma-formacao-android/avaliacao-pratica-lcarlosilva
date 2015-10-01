package com.example.administrador.myapplication.controller.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrador.myapplication.model.entities.Client;
import com.example.administrador.myapplication.R;

import java.util.List;

public class ClientListAdapter extends BaseAdapter {

    private Activity context;
    private List<Client> listaClient;

    public ClientListAdapter(Activity context) {
        this.context = context;
        listaClient = Client.getAll();
    }

    @Override
    public int getCount() {
        return listaClient.size();
    }

    @Override
    public Client getItem(int position) {
        return listaClient.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<Client> getListaClient() {
        return listaClient;
    }

    public void setListaClient(List<Client> listaClient) {
        this.listaClient = listaClient;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = context.getLayoutInflater().inflate(R.layout.client_list_item, parent, false);
        final TextView textViewName = (TextView) view.findViewById(R.id.textViewName);
        final TextView textViewAge = (TextView) view.findViewById(R.id.textViewAge);

        Client item = getItem(position);
        textViewName.setText(item.getName());
        textViewAge.setText(item.getAge().toString());
        return view;

    }
}
