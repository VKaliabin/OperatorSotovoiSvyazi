package project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.ContractDao;
import project.model.ContractEntity;
import project.service.api.ContractService;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    ContractDao contractDao;

    @Override
    @Transactional
    public ContractEntity getContract(int idContract) {
        return contractDao.getContract(idContract);
    }

    @Override
    @Transactional
    public ContractEntity update(ContractEntity contract) {
        return contractDao.update(contract);
    }

    @Override
    @Transactional
    public void deleteConnectOptions(int idContract) {
        contractDao.deleteConnectOptions(idContract);
    }

    @Override
    @Transactional
    public void addContract(ContractEntity contractEntity) {
        contractDao.addContract(contractEntity);
    }

    @Override
    @Transactional
    public List<ContractEntity> list() {
        return contractDao.list();
    }

    @Override
    @Transactional
    public List<ContractEntity> listById(int idClient) {
        return contractDao.listById(idClient);
    }
}
