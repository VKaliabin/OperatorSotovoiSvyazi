package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.ContractDao;
import project.model.ContractEntity;
import project.model.OptionEntity;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    ContractDao contractDao;

    @Transactional
    public ContractEntity getContract(int clientId){
        return contractDao.getContract(clientId);
    }
    @Transactional
    public ContractEntity update(ContractEntity contract){
        return contractDao.update(contract);
    }

    @Override
    @Transactional
    public void deleteConnectOptions(int idContract) {
        contractDao.deleteConnectOptions(idContract);
    }

    @Override
    @Transactional
    public void addConnectOptions(int idContract, int idOption) {
        contractDao.addConnectOptions(idContract,idOption);
    }
}
