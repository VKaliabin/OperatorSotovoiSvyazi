package ClientsTest.services;

import ClientsTest.Model.Clients;
import ClientsTest.dao.ClientDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ClientServiceImpl implements ClientService {
    private ClientDao clientDao;

    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    @Transactional
    public void addClient(Clients client) {
        this.clientDao.addClient(client);
    }

    @Override
    @Transactional
    public void updateClient(Clients client) {
        this.clientDao.updateClient(client);
    }

    @Override
    @Transactional
    public void removeClient(int id) {
        this.clientDao.removeClient(id);
    }

    @Override
    @Transactional
    public Clients getClientId(int id) {
        return this.clientDao.getClientId(id);
    }

    @Override
    @Transactional
    public List<Clients> listClients() {
        return this.clientDao.listClients();
    }
}
