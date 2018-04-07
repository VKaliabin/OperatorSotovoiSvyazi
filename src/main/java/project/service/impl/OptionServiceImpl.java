package project.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.api.OptionDao;
import project.model.OptionEntity;
import project.service.api.OptionService;
import project.utils.OptionModel;
import project.utils.SelectedOptionsModel;

import java.util.ArrayList;
import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {
    private static final Logger logger = LoggerFactory.getLogger(OptionServiceImpl.class);
    @Autowired
    private OptionDao optionDao;

    @Override
    @Transactional
    public List<OptionEntity> listOptions(int idTariff) {
        logger.info("List of options by Tariff ID " + idTariff + " was obtained");
        return optionDao.listOptions(idTariff);
    }

    @Override
    @Transactional
    public List<OptionEntity> listAllOptions() {
        logger.info("List of all options was obtained");
        return optionDao.listAllOptions();
    }

    @Override
    @Transactional
    public void addOption(OptionEntity optionEntity) {
        optionDao.addOption(optionEntity);
        logger.info("Option " + optionEntity.getIdOption() + " was added");
    }

    @Override
    @Transactional
    public OptionEntity update(OptionEntity optionEntity) {
        logger.info("Option " + optionEntity.getIdOption() + " was updated");
        return optionDao.update(optionEntity);
    }

    @Override
    @Transactional
    public void deleteOption(int idOption) {
        optionDao.deleteOption(idOption);
        logger.info("Option " + idOption + " was removed");
    }

    @Override
    @Transactional
    public OptionEntity getOption(int idOption) {
        logger.info("Option " + idOption + " was obtained");
        return optionDao.getOption(idOption);
    }

    @Override
    @Transactional
    public List<SelectedOptionsModel> getOptions(List<OptionEntity> optionEntities, List<OptionEntity> currentOptions) {
        List<SelectedOptionsModel> selected = new ArrayList<>();
        List<OptionEntity> checkedList = new ArrayList<>();

        for (OptionEntity optionEntity : optionEntities) {
            boolean flag = false;
            SelectedOptionsModel selectedOptionsModel = new SelectedOptionsModel();
            for (OptionEntity currentOption : currentOptions) {
                if (optionEntity.getIdOption() == currentOption.getIdOption()) {
                    selectedOptionsModel.setIdOption(optionEntity.getIdOption());
                    selectedOptionsModel.setChacked(true);
                    checkedList.add(optionEntity);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                selectedOptionsModel.setIdOption(optionEntity.getIdOption());
                selectedOptionsModel.setChacked(false);
                if (optionEntity.getCompatibility().equals("Double")) {
                    selectedOptionsModel.setDisable(true);
                }
            }
            selectedOptionsModel.setOptionEntity(generateOptionModel(optionEntity));
            selected.add(selectedOptionsModel);
        }

        for (OptionEntity checked : checkedList) {
            for (SelectedOptionsModel selectedOptionsModel : selected) {
                OptionModel selectedEntity = selectedOptionsModel.getOptionEntity();
                if (checked.getCompatibility().equals("Noncompatible") && checked.getIdOption() != selectedEntity.getIdOption()
                        && selectedEntity.getCompatibility().equals("Noncompatible")) {
                    selectedOptionsModel.setDisable(true);
                }
                if (checked.getCompatibility().equals("Compatible+") && selectedEntity.getCompatibility().equals("Double")) {
                    selectedOptionsModel.setDisable(false);
                }
            }
        }
        logger.info("List of SelectedOptionsModel was obtained");
        return selected;
    }


    private OptionModel generateOptionModel(OptionEntity optionEntity) {
        OptionModel optionModel = new OptionModel();
        optionModel.setIdOption(optionEntity.getIdOption());
        optionModel.setPriceOption(optionEntity.getPriceOption());
        optionModel.setNameOption(optionEntity.getNameOption());
        optionModel.setConnectionCostOption(optionEntity.getConnectionCostOption());
        optionModel.setCompatibility(optionEntity.getCompatibility());
        return optionModel;
    }


    @Override
    @Transactional
    public List<SelectedOptionsModel> getChangedOptions(List<OptionEntity> optionEntities, List<String> checkedList) {
        List<SelectedOptionsModel> selected = new ArrayList<>();
        boolean noncap = false;
        boolean comPlus = false;
        int idDouble = 0;
        List<Integer> notSelectedNoncap = new ArrayList<>();
        List<Integer> doubleList = new ArrayList<>();
        for (OptionEntity optionEntity : optionEntities) {
            SelectedOptionsModel selectedOptionsModel = new SelectedOptionsModel();
            selectedOptionsModel.setIdOption(optionEntity.getIdOption());
            if (optionEntity.getCompatibility().equals("Double")) {
                doubleList.add(optionEntity.getIdOption());
                selectedOptionsModel.setDisable(true);
            }
            if (checkedList.contains(String.valueOf(optionEntity.getIdOption()))) {
                selectedOptionsModel.setChacked(true);
                //Noncompatible
                if (optionEntity.getCompatibility().equals("Noncompatible")) {
                    noncap = true;
                    selectedOptionsModel.setDisable(false);
                    for (SelectedOptionsModel optionsModel : selected) {
                        if (notSelectedNoncap.contains((optionsModel.getIdOption()))) {
                            optionsModel.setDisable(true);
                        }
                    }
                }
                //com+
                if (optionEntity.getCompatibility().equals("Compatible+")) {
                    comPlus = true;
                    selectedOptionsModel.setDisable(false);
                    for (SelectedOptionsModel optionsModel : selected) {
                        if (doubleList.contains(optionsModel.getIdOption())) {
                            optionsModel.setDisable(false);
                        }
                    }
                }
                //double
                if (optionEntity.getCompatibility().equals("Double")) {
                    idDouble = optionEntity.getIdOption();
                    selectedOptionsModel.setDisable(false);
                }
            } else {
                //Noncompatible
                if (optionEntity.getCompatibility().equals("Noncompatible") && noncap) {
                    selectedOptionsModel.setDisable(true);
                } else if (optionEntity.getCompatibility().equals("Noncompatible") && !noncap) {
                    notSelectedNoncap.add(optionEntity.getIdOption());
                }

                //com+
                if (optionEntity.getCompatibility().equals("Double") && comPlus) {
                    selectedOptionsModel.setDisable(false);
                }

                if (optionEntity.getCompatibility().equals("Double") && idDouble != 0) {
                    selectedOptionsModel.setDisable(true);
                }
            }
            selected.add(selectedOptionsModel);
        }
        if (!comPlus) {
            for (SelectedOptionsModel optionsModel : selected) {
                if (doubleList.contains(optionsModel.getIdOption())) {
                    optionsModel.setChacked(false);
                    optionsModel.setDisable(true);
                }
            }
        }
        if (idDouble != 0) {

            for (SelectedOptionsModel optionsModel : selected) {
                if (doubleList.contains(optionsModel.getIdOption()) && idDouble != optionsModel.getIdOption()) {
                    optionsModel.setDisable(true);
                }
            }
        }
        logger.info("Changed list of SelectedOptionsModel was obtained");
        return selected;
    }
}
