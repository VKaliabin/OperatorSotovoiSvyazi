package project.service.api;

import project.model.OptionEntity;
import java.util.List;

public interface OptionService {
     List<OptionEntity> listOptions(int idTariff);

     List<OptionEntity> listAllOptions();

     void addOption(OptionEntity optionEntity);

     OptionEntity update(OptionEntity optionEntity);

     void deleteOption(int idOption);

     OptionEntity getOption(int idOption);

}
