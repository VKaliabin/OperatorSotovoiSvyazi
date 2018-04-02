package project.service.api;

import project.model.ClientEntity;
import java.util.List;

public interface ClientService {

     ClientEntity findByEMail(String clientEmail);

     void addClient(ClientEntity client);

     void updateClient(ClientEntity client);

     void unblockClient(int idClient);

     void blockClient(int idClient);

     void removeClient(int idClient);

     ClientEntity getClientId(int idClient);

     List<ClientEntity> listClients();
}
