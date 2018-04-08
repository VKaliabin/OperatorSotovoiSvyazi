package project.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.api.TariffDao;
import project.model.TariffEntity;
import project.service.api.TariffService;

import java.io.Serializable;
import java.util.List;

@Service
public class TariffServiceImpl implements TariffService {
    private static final Logger logger = LoggerFactory.getLogger(TariffServiceImpl.class);

    /**
     * Provides work with DB by some methods
     */
    @Autowired
    private TariffDao tariffDao;

    /**
     * This method get a list of all tariffs
     *
     * @return - return a list of all tariffs
     */
    @Override
    @Transactional
    public List<TariffEntity> listTariffs() {
        logger.info("List of tariffs was obtained");
        return tariffDao.listTariffs();
    }

    /**
     * This method get a tariff by ID
     *
     * @param idTariff - id of a required tariff
     * @return - return a found tariff
     */
    @Override
    @Transactional
    public TariffEntity getTariff(int idTariff) {
        logger.info("Tariff " + idTariff + " was obtained");
        return tariffDao.getTariff(idTariff);
    }

    /**
     * This method update a tariff
     *
     * @param tariffEntity - a required tariff
     * @return - return a updated tariff
     */
    @Override
    @Transactional
    public TariffEntity update(TariffEntity tariffEntity) {
        logger.info("Tariff " + tariffEntity.getIdTariff() + " was updated");
        return tariffDao.update(tariffEntity);
    }

    /**
     * This method add a new tariff
     *
     * @param tariffEntity - new tariff
     */
    @Override
    @Transactional
    public void add(TariffEntity tariffEntity) {
        tariffDao.add(tariffEntity);
        logger.info("Tariff " + tariffEntity.getIdTariff() + " was added");
    }

    /**
     * This method delete a tariff by ID
     *
     * @param idTariff - id of a required tariff
     */
    @Override
    @Transactional
    public void remove(int idTariff) {
        tariffDao.remove(idTariff);
        logger.info("Tariff " + idTariff + " was removed");
    }
}
