package project.dao;

import project.model.OptionEntity;
import java.util.List;

public interface OptionDao {

    public List<OptionEntity> listOptions(int idTariff);

    public List<OptionEntity> listAllOptions();

    public void addOption(OptionEntity optionEntity);

    public OptionEntity update(OptionEntity optionEntity);

    public void deleteOption(int idOptoin);

    public OptionEntity getOption(int idOption);
}
