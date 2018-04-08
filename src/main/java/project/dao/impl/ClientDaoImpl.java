package project.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.dao.api.ClientDao;
import project.exception.DAOexception;
import project.hibernate.HibernateUtil;
import project.model.ClientEntity;

import java.io.Serializable;
import java.util.List;

/**
 * The layer dao which works with hibernate and describe the work methods with ClientEntity
 * @author MASTER
 */
@Repository
public class ClientDaoImpl implements ClientDao {
    /**
     * this variable is responsible for database work
     * @see HibernateUtil
     */
    @Autowired
    private HibernateUtil hibernateUtil;

    public void setHibernateUtil(HibernateUtil hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
    }

    /**
     * This method searches a client by email
     * @param email - email of a client
     * @return - return the found client
     * @see HibernateUtil#findByEmail(String)
     */
    @Override
    public ClientEntity findByEmailOfEmail(String email) {
        return hibernateUtil.findByEmail(email);
    }

    /**
     * This method add a new client
     * @param client - a new client(ClientEntity)
     * @see HibernateUtil#add(Object)
     */
    @Override
    public void addClient(ClientEntity client) {
        hibernateUtil.add(client);
    }

    /**
     * This method update a client
     * @param client - client(ClientEntity)
     * @see HibernateUtil#update(Object)
     */
    @Override
    public void updateClient(ClientEntity client) {
        hibernateUtil.update(client);
    }

    /**
     * This method remove a client
     * @param idClient - id of a client
     * @see HibernateUtil#delete(Object)
     */
    @Override
    public void removeClient(int idClient) {
        ClientEntity client = new ClientEntity();
        client.setIdClient(idClient);
        hibernateUtil.delete(client);
    }

    /**
     * This method get a neccessary client
     * @param idClient - id of a client
     * @return - return found a client
     * @see HibernateUtil#fetchById(Serializable, Class)
     */
    @Override
    public ClientEntity getClientId(int idClient) {

        return hibernateUtil.fetchById(idClient, ClientEntity.class);
    }

    /**
     * This method get list of all clients
     * @return - return all of clients
     * @see HibernateUtil#fetchAll(Class)
     */
    @Override
    public List<ClientEntity> listClients() {
        List<ClientEntity> clientEntityList = null;
        try {
            clientEntityList = hibernateUtil.fetchAll(ClientEntity.class);
        } catch (DAOexception daOexception) {
            daOexception.printStackTrace();
        }
        return clientEntityList;
    }
}
