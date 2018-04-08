package project.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.dao.api.TariffDao;
import project.exception.DAOexception;
import project.hibernate.HibernateUtil;
import project.model.TariffEntity;

import java.io.Serializable;
import java.util.List;

@Repository
public class TariffDaoImpl implements TariffDao {
    /**
     * this variable is responsible for database work
     *
     * @see HibernateUtil
     */
    @Autowired
    private HibernateUtil hibernateUtil;

    /**
     * This method get a list of all tariffs
     *
     * @return - return a list of all tariffs
     * @see HibernateUtil#fetchAll(Class)
     */
    @Override
    public List<TariffEntity> listTariffs() {
        List<TariffEntity> tariffEntities = null;
        try {
            tariffEntities = hibernateUtil.fetchAll(TariffEntity.class);
        } catch (DAOexception daOexception) {
            daOexception.printStackTrace();
        }
        return tariffEntities;
    }

    /**
     * This method get a tariff by ID
     *
     * @param idTariff - id of a required tariff
     * @return - return a found tariff
     * @see HibernateUtil#fetchById(Serializable, Class)
     */
    @Override
    public TariffEntity getTariff(int idTariff) {
        return hibernateUtil.fetchById(idTariff, TariffEntity.class);
    }

    /**
     * This method update a tariff
     *
     * @param tariffEntity - a required tariff
     * @return - return a updated tariff
     * @see HibernateUtil#update(Object)
     */
    @Override
    public TariffEntity update(TariffEntity tariffEntity) {
        return hibernateUtil.update(tariffEntity);
    }

    /**
     * This method add a new tariff
     *
     * @param tariffEntity - new tariff
     * @see HibernateUtil#add(Object)
     */
    @Override
    public void add(TariffEntity tariffEntity) {
        hibernateUtil.add(tariffEntity);
    }

    /**
     * This method delete a tariff by ID
     *
     * @param idTariff - id of a required tariff
     * @see HibernateUtil#delete(Serializable, Class)
     */
    @Override
    public void remove(int idTariff) {
        hibernateUtil.delete(idTariff, TariffEntity.class);
    }
}
