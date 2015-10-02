package com.example.administrador.myapplication.model.persistence.repository;

import com.example.administrador.myapplication.model.entities.Client;
import com.example.administrador.myapplication.model.persistence.repository.interface_repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;

public class MemoryClientRepository implements ClientRepository {

    private static Long ids = 0L;

    private static MemoryClientRepository SINGLETON_INSTANCE;
    private static List<Client> clients;

    private MemoryClientRepository(){ super();clients = new ArrayList<Client>();}

    public static ClientRepository getInstance(){

        if(SINGLETON_INSTANCE == null){
            SINGLETON_INSTANCE = new MemoryClientRepository();
        }
        return SINGLETON_INSTANCE;
    }

    @Override
    public void save(Client client, Long idAddress) {
        Client retirar;
        if(client.getId() != null){
            alterar(client);
        } else{
            incluir(client);
        }
    }

    private void alterar(Client client) {
        Client retirar = null;
        for(Client item : getAll()) {
            if (item.getId().equals(client.getId())) {
                retirar = item;
            }
        }
        if(retirar != null){
            clients.remove(retirar);
            clients.add(client);
        }
    }

    private void incluir(Client client) {
        client.setId(ids++);
        clients.add(client);
    }

    @Override
    public List<Client> getAll() {
        return clients;
    }

    @Override
    public void delete(Client client) {
        clients.remove(client);
    }
}
