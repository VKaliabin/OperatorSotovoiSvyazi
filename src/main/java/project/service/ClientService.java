package project.service;

import org.springframework.data.jpa.repository.JpaRepository;
import project.model.ClientEntity;

import java.util.List;

public interface ClientService  {
    void save(ClientEntity client);
    ClientEntity findByEMail(String clientEmail);
//    public void addClient(ClientEntity client);
//    public void updateClient(ClientEntity client);
//    public void removeClient(int id);
//    public ClientEntity getClientId (int id);
//    public List<ClientEntity> listClients();
}
