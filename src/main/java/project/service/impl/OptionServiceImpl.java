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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {
    private static final Logger logger = LoggerFactory.getLogger(OptionServiceImpl.class);
    /**
     * The layer which work with hibernate and provides specified methods
     */
    @Autowired
    private OptionDao optionDao;

    /**
     * This method get list of all options of a tariff by id last one
     *
     * @param idTariff - id of required tariff
     * @return - return list of all options for one tariff
     */
    @Override
    @Transactional
    public List<OptionEntity> listOptions(int idTariff) {
        logger.info("List of options by Tariff ID " + idTariff + " was obtained");
        return optionDao.listOptions(idTariff);
    }

    /**
     * This method get list of all options
     *
     * @return - list of options
     */
    @Override
    @Transactional
    public List<OptionEntity> listAllOptions() {
        logger.info("List of all options was obtained");
        return optionDao.listAllOptions();
    }

    /**
     * This method add a option to database
     *
     * @param optionEntity - new option
     */
    @Override
    @Transactional
    public void addOption(OptionEntity optionEntity) {
        optionDao.addOption(optionEntity);
        logger.info("Option " + optionEntity.getIdOption() + " was added");
    }
    /**
     * This method update a option
     *
     * @param optionEntity - required option
     * @return - return updated option
     *
     */
    @Override
    @Transactional
    public OptionEntity update(OptionEntity optionEntity) {
        logger.info("Option " + optionEntity.getIdOption() + " was updated");
        return optionDao.update(optionEntity);
    }
    /**
     * This method delete a option by id
     *
     * @param idOption - id of required option
     *
     */
    @Override
    @Transactional
    public void deleteOption(int idOption) {
        optionDao.deleteOption(idOption);
        logger.info("Option " + idOption + " was removed");
    }
    /**
     * This method get a option
     *
     * @param idOption - id of a required option
     * @return - return found option
     *
     */
    @Override
    @Transactional
    public OptionEntity getOption(int idOption) {
        logger.info("Option " + idOption + " was obtained");
        return optionDao.getOption(idOption);
    }

    /**
     * Method recieve list option (in DTO form) connected with some contract.
     * @param optionEntities - all options
     * @param currentOptions - options which connected with contract
     * @return - return dto model
     */
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

    /**
     * Generate dto model (Option Model) from entity (Option Entity)
     * @param optionEntity - some option
     * @return - dto model from entity
     */
    private OptionModel generateOptionModel(OptionEntity optionEntity) {
        OptionModel optionModel = new OptionModel();
        optionModel.setIdOption(optionEntity.getIdOption());
        optionModel.setPriceOption(optionEntity.getPriceOption());
        optionModel.setNameOption(optionEntity.getNameOption());
        optionModel.setConnectionCostOption(optionEntity.getConnectionCostOption());
        optionModel.setCompatibility(optionEntity.getCompatibility());
        return optionModel;
    }

    /**
     * Method recieve list option (in DTO form) connected with some contract. It's using in ajax
     * @param optionEntities - list of all options
     * @param checkedList - choosed value (option which has been choosed by user)
     * @return - return dto model which applying in json
     */
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
