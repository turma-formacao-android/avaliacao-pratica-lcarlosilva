package com.example.administrador.myapplication.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.administrador.myapplication.model.persistence.repository.SQLiteUserRepositoy;

import java.util.List;

public class User implements Parcelable {

    private Long idUser;
    private String nameUser;
    private String passwordUser;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static List<User> getAll() {
        return SQLiteUserRepositoy.getInstance().getAll();
    }

    public static void delete(User user) {
        SQLiteUserRepositoy.getInstance().delete(user);
    }
}
