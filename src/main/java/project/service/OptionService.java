package project.service;

import project.model.OptionEntity;
import java.util.List;

public interface OptionService {
    public List<OptionEntity> listOptions(int idTariff);

    public List<OptionEntity> listAllOptions();

    public void addOption(OptionEntity optionEntity);

    public OptionEntity update(OptionEntity optionEntity);

    public void deleteOption(int idOption);

   public OptionEntity getOption(int idOption);
}
