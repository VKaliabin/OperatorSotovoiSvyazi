package project.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.dao.api.Dao;
import project.hibernate.HibernateUtil;

import java.util.List;

@Repository
public class DaoImpl implements Dao {
    @Autowired
    private HibernateUtil hibernateUtil;

    @Override
    public void deleteConnectOptions(int idContract) {
        String query = "delete from connected_options where idContract=:id";
        hibernateUtil.deleteConnecOption(query, idContract);
    }

    @Override
    public <T> T findByEmailOfEmail(String email) {
        return (T) hibernateUtil.findByEmail(email);
    }

    @Override
    public <T> void add(Class<T> entity) {
        hibernateUtil.add(entity);
    }

    @Override
    public <T> T update(Class<T> entity) {
        return (T) hibernateUtil.update(entity);
    }

    @Override
    public <T> void remove(int id, Class<T> entityClass) {
        hibernateUtil.delete(id, entityClass);
    }

    @Override
    public <T> T get(int id, Class<T> entityClass) {
        return hibernateUtil.fetchById(id, entityClass);
    }

    @Override
    public <T> List<T> list(Class<T> entityClass) {
        return hibernateUtil.fetchAll(entityClass);
    }

    @Override
    public <T> List<T> list(String query, Class<T> entityClass, int id) {

        return hibernateUtil.fetchAllById(query, entityClass, id);
    }
}
