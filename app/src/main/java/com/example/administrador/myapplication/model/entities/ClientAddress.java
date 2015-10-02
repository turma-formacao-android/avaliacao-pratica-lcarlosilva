package com.example.administrador.myapplication.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class ClientAddress implements Parcelable {

    private Long id;
    private String cep;
    private String tipoDeLogradouro;
    private String logradouro;
    private String bairro;
    private int numero;
    private String complemento;
    private String cidade;
    private String estado;

    public ClientAddress() {
        super();
    }

    public ClientAddress(Parcel in) {
        super();
        readToparcel(in);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTipoDeLogradouro() {
        return tipoDeLogradouro;
    }

    public void setTipoDeLogradouro(String tipoDeLogradouro) {
        this.tipoDeLogradouro = tipoDeLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public static final Creator<ClientAddress> CREATOR = new Creator<ClientAddress>() {
        @Override
        public ClientAddress createFromParcel(Parcel in) {
            return new ClientAddress(in);
        }

        @Override
        public ClientAddress[] newArray(int size) {
            return new ClientAddress[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(cep);
        dest.writeString(tipoDeLogradouro);
        dest.writeString(logradouro);
        dest.writeString(bairro);
        dest.writeInt(numero);
        dest.writeString(complemento);
        dest.writeString(cidade);
        dest.writeString(estado);
    }

    private void readToparcel(Parcel parcel) {
        id = parcel.readLong();
        cep = parcel.readString();
        tipoDeLogradouro = parcel.readString();
        logradouro = parcel.readString();
        bairro = parcel.readString();
        numero = parcel.readInt();
        complemento = parcel.readString();
        cidade = parcel.readString();
        estado = parcel.readString();
    }
}
