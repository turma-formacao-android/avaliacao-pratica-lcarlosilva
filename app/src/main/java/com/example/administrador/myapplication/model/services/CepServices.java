package com.example.administrador.myapplication.model.services;

import com.example.administrador.myapplication.model.entities.ClientAddress;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public final class CepServices {

    private static final String URL = "http://correiosapi.apphb.com/cep/";

    private CepServices(){
        super();
    }

    public static ClientAddress getAddressBy(String cep){
        ClientAddress address = null;
        try {
            URL url = new URL(URL + cep);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

            if(responseCode != HttpURLConnection.HTTP_OK){
                throw new RuntimeException("Error code: " + responseCode);
            }

            InputStream inputStream = conn.getInputStream();//por aqui se captura o BODY

            ObjectMapper objectMapper = new ObjectMapper();
            address = objectMapper.readValue(inputStream, ClientAddress.class);

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return address;
    }
}
