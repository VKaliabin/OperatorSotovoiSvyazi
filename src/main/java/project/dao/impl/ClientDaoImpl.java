package project.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.dao.api.ClientDao;
import project.hibernate.HibernateUtil;
import project.model.ClientEntity;

import java.util.List;

@Repository
public class ClientDaoImpl implements ClientDao {

    @Autowired
    private HibernateUtil hibernateUtil;

    public void setHibernateUtil(HibernateUtil hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
    }

    @Override
    public ClientEntity findByEmailOfEmail(String email) {
        return hibernateUtil.findByEmail(email);
    }

    @Override
    public void addClient(ClientEntity client) {
        hibernateUtil.add(client);
    }

    @Override
    public void updateClient(ClientEntity client) {
        hibernateUtil.update(client);
    }

    @Override
    public void removeClient(int id) {
        ClientEntity client = new ClientEntity();
        client.setIdClient(id);
        hibernateUtil.delete(client);
    }

    @Override
    public ClientEntity getClientId(int id) {

        return hibernateUtil.fetchById(id, ClientEntity.class);
    }

    @Override
    public List<ClientEntity> listClients() {
        return hibernateUtil.fetchAll(ClientEntity.class);
    }
}
