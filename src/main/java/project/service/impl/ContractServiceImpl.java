package project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.api.ContractDao;
import project.model.ClientEntity;
import project.model.ContractEntity;
import project.model.TariffEntity;
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
    public void blockContract(int idContract) {
        ContractEntity contractEntity = getContract(idContract);
        contractEntity.setBlockedContract("Blocked");
        contractDao.update(contractEntity);
    }

    @Override
    @Transactional
    public void unblockContract(int idContract) {
        ContractEntity contractEntity = getContract(idContract);
        contractEntity.setBlockedContract("Unblocked");
        contractDao.update(contractEntity);
    }

    @Override
    @Transactional
    public void addContract(ContractEntity contractEntity, TariffEntity tariffEntity, ClientEntity clientEntity) {
        contractEntity.setTariff(tariffEntity);
        contractEntity.setBlockedContract("Unblocked");
        contractEntity.setAdminBlock("N");
        contractEntity.setClientEntity(clientEntity);
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
