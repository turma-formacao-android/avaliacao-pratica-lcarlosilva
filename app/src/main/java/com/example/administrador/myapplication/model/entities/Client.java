package com.example.administrador.myapplication.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.administrador.myapplication.model.persistence.repository.SQLiteClientAddressRepository;
import com.example.administrador.myapplication.model.persistence.repository.SQLiteClientRepositoy;

import java.util.List;

public class Client implements Parcelable {

    private Long id;
    private String name;
    private Integer age;
    private String phone;
    private Long address;

    private ClientAddress clientAddress;

    public Client() {
    }

    public Client(Parcel parcel) {
        super();
        readToParcel(parcel);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getAddress() {
        return address;
    }

    public void setAddress(Long address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void save() {
        SQLiteClientRepositoy.getInstance().save(this);
    }

    public static List<Client> getAll() {
        return SQLiteClientRepositoy.getInstance().getAll();
    }

    public static void delete(Client client) {
        SQLiteClientRepositoy.getInstance().delete(client);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (!id.equals(client.id)) return false;
        if (!name.equals(client.name)) return false;
        if (!age.equals(client.age)) return false;
        if (!phone.equals(client.phone)) return false;
        return address.equals(client.address);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + age.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name == null ? "" : name);
        dest.writeInt(age == null ? -1 : age.intValue());
        dest.writeString(phone == null ? "" : phone);
        dest.writeLong(address);
    }

    private void readToParcel(Parcel parcel) {
        id = parcel.readLong();
        name = parcel.readString();
        age = parcel.readInt();
        if (age == -1) age = null;
        phone = parcel.readString();
        address =  parcel.readLong();
    }

    public static final Parcelable.Creator<Client> CREATOR = new Parcelable.Creator<Client>() {

        @Override
        public Client createFromParcel(Parcel source) {
            return new Client(source);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

}
