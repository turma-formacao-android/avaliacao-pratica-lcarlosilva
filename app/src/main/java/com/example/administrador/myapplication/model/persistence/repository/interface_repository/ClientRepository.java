package com.example.administrador.myapplication.model.persistence.repository.interface_repository;

import com.example.administrador.myapplication.model.entities.Client;

import java.util.List;

public interface ClientRepository {

    void save(Client client, Long idAddress);
    List<Client> getAll();
    void delete(Client cliente);

}


