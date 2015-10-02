package com.example.administrador.myapplication.model.persistence.repository.interface_repository;

import com.example.administrador.myapplication.model.entities.ClientAddress;

import java.util.List;

public interface ClientAddressRepository {

    Long save(ClientAddress address);
    List<ClientAddress> getAll();
}
