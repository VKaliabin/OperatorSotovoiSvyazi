package project.dao.api;


import project.model.ClientEntity;

import java.util.List;

public interface ClientDao {

     ClientEntity findByEmailOfEmail(String jj);

     void addClient(ClientEntity client);

     void updateClient(ClientEntity client);

     void removeClient(int id);

     ClientEntity getClientId(int id);

     List<ClientEntity> listClients();


}
