package project.hibernate;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import project.exception.DAOexception;
import project.model.ClientEntity;

import java.io.Serializable;
import java.util.List;

@Repository
public class HibernateUtil {

    private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
    /**
     * the factory for creating Hibernate session
     */
    private static SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * This method save an object(entity) to current session (to database)
     *
     * @param entity - saved object
     * @param <T>    - type of an object
     * @return - return serializable object
     */
    public <T> Serializable add(final T entity) {
        return sessionFactory.getCurrentSession().save(entity);
    }

    /**
     * This method update an object(entity) in current session(in database)
     *
     * @param entity - updated object
     * @param <T>    - type of an object
     * @return - return serializable object
     */
    public <T> T update(final T entity) {
        sessionFactory.getCurrentSession().update(entity);
        return entity;
    }

    /**
     * This method delete an object(entity) in current session(in database)
     *
     * @param entity - deleted object
     * @param <T>    - type of an object
     */
    public <T> void delete(final T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    /**
     * This method delete an object(entity) in current session(in database) with specified ID
     *
     * @param id          - specified ID of an object
     * @param entityClass - deleted object
     * @param <T>         - type of an object
     */
    public <T> void delete(Serializable id, Class<T> entityClass) {
        T entity = fetchById(id, entityClass);
        delete(entity);
    }

    /**
     * This method find an object(entity) in current session(in database) with required email
     *
     * @param email - the value which is owned a client
     * @return - return a found client
     */
    public ClientEntity findByEmail(String email) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("from ClientEntity where EMAIL=:email");
        query.setParameter("email", email);

        return (ClientEntity) query.uniqueResult();
    }

    /**
     * This method get a list of objects(entities) in current session(in database) with specified ID
     * some object and specified request(string)
     *
     * @param query       - specified request to database
     * @param entityClass - desired object
     * @param id          - specified ID of an object
     * @param <T>         - type of objects
     * @return - return list of desired objects
     */
    public <T> List fetchAllById(String query, Class<T> entityClass, int id) throws DAOexception {
        SQLQuery sql = sessionFactory
                .getCurrentSession()
                .createSQLQuery(query)
                .addEntity(entityClass);
        sql.setParameter("id", id);
        if (sql.list().isEmpty()) throw new DAOexception("Can not fetch all by id");
        return sql.list();
    }

    /**
     * This method delete options which associated with some object by ID according a request(String)
     *
     * @param query - specified request to database
     * @param id    - ID some object which associated with options
     */
    public void deleteConnecOption(String query, int id) throws DAOexception {
        SQLQuery sqlQuery = sessionFactory
                .getCurrentSession()
                .createSQLQuery(query);
        sqlQuery.setParameter("id", id);
        throw new DAOexception("Can not delete connected option");

    }

    /**
     * This method get a list of all objects
     *
     * @param entityClass - desired object
     * @param <T>         - type of an object
     * @return - return a list of all objects
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> fetchAll(Class<T> entityClass) throws DAOexception {
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery(" FROM " + entityClass.getName());
        if (query.list().isEmpty()) throw new DAOexception("Can not fetch ALL");
        return query.list();

    }

    /**
     * This method delete all objects which are associated with another objects by ID and according a
     * request(String)
     *
     * @param id    - ID desired object
     * @param query - specified request
     */
    public void deleteAllById(Serializable id, String query) {
        sessionFactory.getCurrentSession().createSQLQuery(query);
    }

    /**
     * This method get all objects according request
     *
     * @param query - request to database
     * @param <T>   - type of required objects
     * @return - return list of all desired objects
     */
    @SuppressWarnings("rawtypes")
    public <T> List fetchAll(String query) {
        return sessionFactory.getCurrentSession().createSQLQuery(query).list();
    }

    /**
     * This method get an object by ID
     *
     * @param id          - ID of desired object
     * @param entityClass - object
     * @param <T>         - type of object
     * @return - return found object
     */
    @SuppressWarnings("unchecked")
    public <T> T fetchById(Serializable id, Class<T> entityClass) {
        return (T) sessionFactory.getCurrentSession().get(entityClass, id);
    }
}
