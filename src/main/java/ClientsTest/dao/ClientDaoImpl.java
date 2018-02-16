package ClientsTest.dao;

import ClientsTest.Model.Clients;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ClientDaoImpl implements ClientDao {
    private static final Logger logger = LoggerFactory.getLogger(ClientDaoImpl.class);//проверить на this

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void addClient(Clients client) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(client);
        logger.info("Client successfully saved. Client details: " + client);
    }

    @Override
    public void updateClient(Clients client) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(client);
        logger.info("Client successfully updated. Client details: " + client);
    }

    @Override
    public void removeClient(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Clients client = (Clients)session.load(Clients.class, new Integer(id));
        if(client!=null){
            session.delete(client);
        }
        logger.info("Client successfully removed. Client details: " + client);
    }

    @Override
    public Clients getClientId(int id) {
        Session session =this.sessionFactory.getCurrentSession();
        Clients client = (Clients)session.load(Clients.class, new Integer(id));
        logger.info("Client successfully loaded. Client details: " + client);

        return client;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Clients> listClients() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Clients> clientsList = session.createQuery("from clients").list();

        for(Clients client: clientsList){
            logger.info("Client list: " + client);
        }

        return clientsList;
    }
}
