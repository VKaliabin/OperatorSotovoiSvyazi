package project.service.api;

import project.model.ClientEntity;
import java.util.List;

public interface ClientService {

     ClientEntity findByEMail(String clientEmail);

     void addClient(ClientEntity client);

     void updateClient(ClientEntity client);

     void removeClient(int id);

     ClientEntity getClientId(int id);

     List<ClientEntity> listClients();
}
