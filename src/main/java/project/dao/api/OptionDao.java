package project.dao.api;

import project.model.OptionEntity;
import java.util.List;

public interface OptionDao {

     List<OptionEntity> listOptions(int idTariff);

     List<OptionEntity> listAllOptions();

     void addOption(OptionEntity optionEntity);

     OptionEntity update(OptionEntity optionEntity);

     void deleteOption(int idOptoin);

     OptionEntity getOption(int idOption);

}
