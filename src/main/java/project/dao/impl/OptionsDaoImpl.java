package project.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.dao.api.OptionDao;
import project.exception.DAOexception;
import project.hibernate.HibernateUtil;
import project.model.OptionEntity;

import java.io.Serializable;
import java.util.List;

@Repository
public class OptionsDaoImpl implements OptionDao {
    /**
     * this variable is responsible for database work
     *
     * @see HibernateUtil
     */
    @Autowired
    private HibernateUtil hibernateUtil;

    /**
     * This method get list of all options of a tariff by id last one
     *
     * @param idTariff - id of required tariff
     * @return - return list of all options for one tariff
     * @see HibernateUtil#fetchAllById(String, Class, int)
     */
    @Override
    public List<OptionEntity> listOptions(int idTariff) {
        String query = "select * from options where idTARIFF=:id";
        List<OptionEntity> options = null;
        try {
            options = hibernateUtil.fetchAllById(query, OptionEntity.class, idTariff);
        } catch (DAOexception daOexception) {
            daOexception.printStackTrace();
        }
        return options;
    }

    /**
     * This method get list of all options
     *
     * @return - list of options
     * @see HibernateUtil#fetchAll(Class)
     */
    @Override
    public List<OptionEntity> listAllOptions() {
        List<OptionEntity> optionEntities = null;
        try {
            optionEntities = hibernateUtil.fetchAll(OptionEntity.class);
        } catch (DAOexception daOexception) {
            daOexception.printStackTrace();
        }
        return optionEntities;
    }

    /**
     * This method add a option to database
     *
     * @param optionEntity - new option
     * @see HibernateUtil#add(Object)
     */
    @Override
    public void addOption(OptionEntity optionEntity) {
        hibernateUtil.add(optionEntity);
    }

    /**
     * This method update a option
     *
     * @param optionEntity - required option
     * @return - return updated option
     * @see HibernateUtil#update(Object)
     */
    @Override
    public OptionEntity update(OptionEntity optionEntity) {
        return hibernateUtil.update(optionEntity);
    }

    /**
     * This method delete a option by id
     *
     * @param idOptoin - id of required option
     * @see HibernateUtil#delete(Serializable, Class)
     */
    @Override
    public void deleteOption(int idOptoin) {
        hibernateUtil.delete(idOptoin, OptionEntity.class);
    }

    /**
     * This method get a option
     *
     * @param idOption - id of a required option
     * @return - return found option
     * @see HibernateUtil#fetchById(Serializable, Class)
     */
    @Override
    public OptionEntity getOption(int idOption) {
        return hibernateUtil.fetchById(idOption, OptionEntity.class);
    }

}
