package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.ClientDao;
import project.dao.TariffDao;
import project.model.OptionEntity;
import project.model.TariffEntity;

import java.util.List;
@Service
public class TariffServiceImpl implements TariffService {
    @Autowired
    private TariffDao tariffDao;
    @Override
    @Transactional
    public List<TariffEntity> listTariffs(){
        return tariffDao.listTariffs();
    }
    @Override
    @Transactional
    public TariffEntity getTariff(int idTariff){
        return tariffDao.getTariff(idTariff);
    }

    @Override
    @Transactional
    public TariffEntity update(TariffEntity tariffEntity) {
        return tariffDao.update(tariffEntity);
    }

    @Override
    @Transactional
    public void add(TariffEntity tariffEntity) {
        tariffDao.add(tariffEntity);
    }

    @Override
    @Transactional
    public void remove(int idTariff) {
        tariffDao.remove(idTariff);
    }
}
