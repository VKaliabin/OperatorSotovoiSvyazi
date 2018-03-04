package project.service.api;

import project.model.TariffEntity;
import java.util.List;

public interface TariffService {

     List<TariffEntity> listTariffs();

     TariffEntity getTariff(int idTariff);

     TariffEntity update(TariffEntity tariffEntity);

     void add(TariffEntity tariffEntity);

     void remove(int idTariff);
}
