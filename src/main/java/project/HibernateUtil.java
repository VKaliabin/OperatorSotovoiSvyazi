package project;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.model.ClientEntity;
import project.model.ContractEntity;
import project.model.OptionEntity;

import java.io.Serializable;
import java.util.List;

@Repository
public class HibernateUtil {


    private static SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public <T> Serializable add(final T entity) {
        return sessionFactory.getCurrentSession().save(entity);
    }

    public <T> T update(final T entity) {
        sessionFactory.getCurrentSession().update(entity);
        return entity;
    }

    public <T> void delete(final T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    public <T> void delete(Serializable id, Class<T> entityClass) {
        T entity = fetchById(id, entityClass);
        delete(entity);
    }

    public ClientEntity findByEmail(String email) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("from ClientEntity where EMAIL=:email");
        query.setParameter("email", email);

        return (ClientEntity) query.uniqueResult(); }

    public <T> List fetchAllById(String query, int id) {
        SQLQuery sql =sessionFactory.getCurrentSession().createSQLQuery(query);
        sql.setParameter("id", id);
        return sql.list();
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> fetchAll(Class<T> entityClass) {
        return sessionFactory.getCurrentSession().createQuery(" FROM " + entityClass.getName()).list();
    }

    @SuppressWarnings("rawtypes")
    public <T> List fetchAll(String query) {
        return sessionFactory.getCurrentSession().createSQLQuery(query).list();
    }

    @SuppressWarnings("unchecked")
    public <T> T fetchById(Serializable id, Class<T> entityClass) {
        return (T) sessionFactory.getCurrentSession().get(entityClass, id);
    }
}
