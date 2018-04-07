package project.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.api.ClientDao;
import project.model.ClientEntity;
import project.model.RoleEntity;
import project.service.api.ClientService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


@Service
public class ClientServiceImpl implements ClientService {
    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);
    @Autowired
    private ClientDao clientDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    @Transactional
    public void addClient(ClientEntity client) {
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setIdRoles(2);
        roleEntity.setTypeUser("ROLE_USER");
        client.setRoles(new HashSet<>(Arrays.asList(roleEntity)));
        clientDao.addClient(client);
        logger.info("Client " + client.getIdClient() + " was added");
    }

    @Override
    @Transactional
    public void unblockClient(int idClient) {
        ClientEntity clientEntity = getClientId(idClient);
        clientEntity.setExistingClient("Unblocked");
        clientDao.updateClient(clientEntity);
        logger.info("Client " + idClient + " was unblocked");
    }

    @Override
    @Transactional
    public void blockClient(int idClient) {
        ClientEntity clientEntity = getClientId(idClient);
        clientEntity.setExistingClient("Blocked");
        clientDao.updateClient(clientEntity);
        logger.info("Client " + idClient + " was blocked");
    }

    @Override
    @Transactional
    public ClientEntity findByEMail(String clientEmail) {
        logger.info("Client with" + clientEmail + " is searching");
        return clientDao.findByEmailOfEmail(clientEmail);
    }

    @Override
    @Transactional
    public void updateClient(ClientEntity client) {
        clientDao.updateClient(client);
        logger.info("Client " + client.getIdClient() + " was updated");
    }

    @Override
    @Transactional
    public void removeClient(int id) {
        clientDao.removeClient(id);
        logger.info("Client " + id + " was removed");
    }

    @Override
    @Transactional
    public ClientEntity getClientId(int id) {
        logger.info("Client " + id + " was obtained");
        return clientDao.getClientId(id);
    }

    @Override
    @Transactional
    public List<ClientEntity> listClients() {
        logger.info("List of all client was obtained");
        return clientDao.listClients();
    }
}
