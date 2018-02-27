package project.hibernate;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import project.model.ClientEntity;
import java.io.Serializable;
import java.util.List;

@Repository
public class HibernateUtil {

    private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
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

    public <T> List fetchAllById(String query, Class<T> entityClass, int id) {
        SQLQuery sql =sessionFactory.getCurrentSession().createSQLQuery(query).addEntity(entityClass);
        sql.setParameter("id", id);
        return sql.list();
    }

    public void deleteConnecOption(String query, int id){
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(query);
        sqlQuery.setParameter("id", id);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> fetchAll(Class<T> entityClass) {
        return sessionFactory.getCurrentSession().createQuery(" FROM " + entityClass.getName()).list();

    }
    public void deleteAllById(Serializable id,String query){
        sessionFactory.getCurrentSession().createSQLQuery(query);
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
