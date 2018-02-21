package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.ClientDao;
import project.dao.RoleDao;
import project.model.ClientEntity;
import project.model.RoleEntity;

import javax.management.relation.Role;
import javax.management.relation.RoleInfoNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
//@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(ClientEntity client) {
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setIdRoles(2);
        roleEntity.setTypeUser("ROLE_USER");
//        roleDao.getOne(2);
        client.setRoles(new HashSet<>(Arrays.asList(roleEntity)));
        clientDao.save(client);
    }
    @Override
    public ClientEntity findByEMail(String clientEmail) {
        return clientDao.findByEmailOfEmail(clientEmail);
    }

//    @Override
//    public void addClient(ClientEntity client) {
//        clientDao.addClient(client);
//    }
//
//    @Override
//    public void updateClient(ClientEntity client) {
//        clientDao.updateClient(client);
//    }
//
//    @Override
//    public void removeClient(int id) {
//        clientDao.removeClient(id);
//    }
//
//    @Override
//    public ClientEntity getClientId(int id) {
//        return clientDao.getClientId(id);
//    }
//
//    @Override
//    public List<ClientEntity> listClients() {
//        return clientDao.listClients();
//    }
}
