package project.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.dao.api.TariffDao;
import project.hibernate.HibernateUtil;
import project.model.TariffEntity;
import java.util.List;

@Repository
public class TariffDaoImpl implements TariffDao {
    @Autowired
    private HibernateUtil hibernateUtil;

    @Override
    public List<TariffEntity> listTariffs() {
        return hibernateUtil.fetchAll(TariffEntity.class);
    }

    @Override
    public TariffEntity getTariff(int idTariff) {
        return hibernateUtil.fetchById(idTariff, TariffEntity.class);
    }

    @Override
    public TariffEntity update(TariffEntity tariffEntity) {
        return hibernateUtil.update(tariffEntity);
    }

    @Override
    public void add(TariffEntity tariffEntity) {
        hibernateUtil.add(tariffEntity);
    }

    @Override
    public void remove(int idTariff) {
        hibernateUtil.delete(idTariff, TariffEntity.class);
    }
}
