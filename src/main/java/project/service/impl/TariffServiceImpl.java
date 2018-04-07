package project.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.api.TariffDao;
import project.model.TariffEntity;
import project.service.api.TariffService;

import java.util.List;

@Service
public class TariffServiceImpl implements TariffService {
    private static final Logger logger = LoggerFactory.getLogger(TariffServiceImpl.class);

    @Autowired
    private TariffDao tariffDao;

    @Override
    @Transactional
    public List<TariffEntity> listTariffs() {
        logger.info("List of tariffs was obtained");
        return tariffDao.listTariffs();
    }

    @Override
    @Transactional
    public TariffEntity getTariff(int idTariff) {
        logger.info("Tariff " + idTariff + " was obtained");
        return tariffDao.getTariff(idTariff);
    }

    @Override
    @Transactional
    public TariffEntity update(TariffEntity tariffEntity) {
        logger.info("Tariff " + tariffEntity.getIdTariff() + " was updated");
        return tariffDao.update(tariffEntity);
    }

    @Override
    @Transactional
    public void add(TariffEntity tariffEntity) {
        tariffDao.add(tariffEntity);
        logger.info("Tariff " + tariffEntity.getIdTariff() + " was added");

    }

    @Override
    @Transactional
    public void remove(int idTariff) {
        tariffDao.remove(idTariff);
        logger.info("Tariff " + idTariff + " was removed");

    }
}
