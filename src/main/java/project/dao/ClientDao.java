package project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import project.model.ClientEntity;

import java.util.List;

public interface ClientDao extends JpaRepository<ClientEntity, Integer> {
//    void save(ClientEntity client);
    ClientEntity findByEmailOfEmail(String jj);
//    public void addClient(ClientEntity client);
//    public void updateClient(ClientEntity client);
//    public void removeClient(int id);
//    public ClientEntity getClientId (int id);
//    public List<ClientEntity> listClients();



}
