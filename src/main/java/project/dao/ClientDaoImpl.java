//package project.dao;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import project.HibernateUtil;
//import project.model.ClientEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class ClientDaoImpl implements ClientDao{
//    private static final Logger logger = LoggerFactory.getLogger(ClientDaoImpl.class);
////
//    @Override
//    public void save(ClientEntity client) {
//        hibernateUtil.add(client);
//    }
////
////    @Override
////    public ClientEntity findByClientEmail(String clientEmail) {
////        return null;
////    }
//
//    @Autowired
//    private HibernateUtil hibernateUtil;
//
//    @Override
//    public ClientEntity findByEmail(String email) {
//       return hibernateUtil.findByEmail(email);
//    }
//
//    //    @Override
////    public void addClient(ClientEntity client) {
////        hibernateUtil.add(client);
////        logger.info("Client successfully added. Client details: " + client);
////    }
////
////    @Override
////    public void updateClient(ClientEntity client) {
////         hibernateUtil.update(client);
////        logger.info("Client successfully updated. Client details: " + client);
////    }
////
////    @Override
////    public void removeClient(int id) {
////        ClientEntity client = new ClientEntity();
////        client.setIdClient(id);
////        hibernateUtil.delete(client);
////        logger.info("Client successfully removed. Client details: " + client);
////    }
////
////    @Override
////    public ClientEntity getClientId(int id) {
////        ClientEntity client = new ClientEntity();
////        hibernateUtil.fetchById(id, ClientEntity.class);
////        logger.info("Client successfully loaded. Client details: " + client);
////        return client;
////
////    }
////
////    @Override
////    public List<ClientEntity> listClients() {
////        List<ClientEntity> clientsList = hibernateUtil.fetchAll(ClientEntity.class);
////        for (ClientEntity client: clientsList){
////            logger.info("Client list: " + client);
////        }
////        return clientsList;
////    }
//}
