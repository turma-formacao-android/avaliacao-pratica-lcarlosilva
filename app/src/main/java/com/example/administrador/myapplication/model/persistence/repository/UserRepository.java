package com.example.administrador.myapplication.model.persistence.repository;

import com.example.administrador.myapplication.model.entities.User;

import java.util.List;

public interface UserRepository {

    void save(User user);
    List<User> getAll();
    void delete(User user);

}


