package project.dao;

import project.model.OptionEntity;
import project.model.TariffEntity;

import java.util.List;

public interface TariffDao {

    public List<TariffEntity> listTariffs();
    public TariffEntity getTariff(int idTariff);

    public TariffEntity update(TariffEntity tariffEntity);

    public void add(TariffEntity tariffEntity);
}
