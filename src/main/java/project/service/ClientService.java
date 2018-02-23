package project.service;


import project.model.ClientEntity;

import java.util.List;

public interface ClientService  {
//    public void save(ClientEntity client);
    public ClientEntity findByEMail(String clientEmail);
    public void addClient(ClientEntity client);
//    public void updateClient(ClientEntity client);
//    public void removeClient(int id);
//    public ClientEntity getClientId (int id);
//    public List<ClientEntity> listClients();
}
