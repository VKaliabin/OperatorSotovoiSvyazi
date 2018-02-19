package project.dao;

import project.model.ClientEntity;

import java.util.List;

public interface ClientDao {
    void save(ClientEntity client);
    ClientEntity findByClientEmail(String clientEmail);
    public void addClient(ClientEntity client);
    public void updateClient(ClientEntity client);
    public void removeClient(int id);
    public ClientEntity getClientId (int id);
    public List<ClientEntity> listClients();
}
