package project.service.api;

import project.model.OptionEntity;
import project.utils.OptionModel;
import project.utils.SelectedOptionsModel;

import java.util.List;

public interface OptionService {
     List<OptionEntity> listOptions(int idTariff);

     List<OptionEntity> listAllOptions();

     void addOption(OptionEntity optionEntity);

     OptionEntity update(OptionEntity optionEntity);

     void deleteOption(int idOption);

     OptionEntity getOption(int idOption);

     List<SelectedOptionsModel> getOptions(List<OptionEntity> optionEntities, List<OptionEntity> currentOptions);

     List<SelectedOptionsModel> getChangedOptions(List<OptionEntity> optionEntities, List<String> checkedList);

}
