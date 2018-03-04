package project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.ClientDao;
import project.model.ClientEntity;
import project.model.RoleEntity;
import project.service.api.ClientService;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


@Service
public class ClientServiceImpl implements ClientService {

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
    }

    @Override
    @Transactional
    public ClientEntity findByEMail(String clientEmail) {
        return clientDao.findByEmailOfEmail(clientEmail);
    }

    @Override
    @Transactional
    public void updateClient(ClientEntity client) {
        clientDao.updateClient(client);
    }

    @Override
    @Transactional
    public void removeClient(int id) {
        clientDao.removeClient(id);
    }

    @Override
    @Transactional
    public ClientEntity getClientId(int id) {
        return clientDao.getClientId(id);
    }

    @Override
    @Transactional
    public List<ClientEntity> listClients() {
        return clientDao.listClients();
    }
}
