package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.ContractDao;
import project.model.ContractEntity;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    ContractDao contractDao;

    @Override
    @Transactional
    public ContractEntity getContract(int clientId){
        return contractDao.getContract(clientId);
    }

    @Override
    @Transactional
    public ContractEntity update(ContractEntity contract){
        return contractDao.update(contract);
    }

    @Override
    @Transactional
    public void deleteConnectOptions(int idContract) {
        contractDao.deleteConnectOptions(idContract);
    }

}
