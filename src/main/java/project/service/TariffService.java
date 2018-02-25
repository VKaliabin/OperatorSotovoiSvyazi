package project.service;

import project.model.TariffEntity;

import java.util.List;

public interface TariffService {
    public List<TariffEntity> listTariffs();

    public TariffEntity getTariff(int idTariff);

    public TariffEntity update(TariffEntity tariffEntity);

    public void add(TariffEntity tariffEntity);
}
