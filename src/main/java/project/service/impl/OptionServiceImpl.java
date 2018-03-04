package project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.OptionDao;
import project.model.OptionEntity;
import project.service.api.OptionService;
import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {
    @Autowired
    private OptionDao optionDao;

    @Override
    @Transactional
    public List<OptionEntity> listOptions(int idTariff) {
        return optionDao.listOptions(idTariff);
    }

    @Override
    @Transactional
    public List<OptionEntity> listAllOptions() {
        return optionDao.listAllOptions();
    }

    @Override
    @Transactional
    public void addOption(OptionEntity optionEntity) {
        optionDao.addOption(optionEntity);
    }

    @Override
    @Transactional
    public OptionEntity update(OptionEntity optionEntity) {
        return optionDao.update(optionEntity);
    }

    @Override
    @Transactional
    public void deleteOption(int idOption) {
        optionDao.deleteOption(idOption);
    }

    @Override
    @Transactional
    public OptionEntity getOption(int idOption) {
        return optionDao.getOption(idOption);
    }

}
