package project.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(ContractServiceImpl.class);
    @Autowired
    ContractDao contractDao;

    @Override
    @Transactional
    public ContractEntity getContract(int idContract) {
        logger.info("Contract " + idContract + " was obtained");
        return contractDao.getContract(idContract);
    }

    @Override
    @Transactional
    public ContractEntity update(ContractEntity contract) {
        logger.info("Contract " + contract.getIdContract() + " was updated");
        return contractDao.update(contract);
    }

    @Override
    @Transactional
    public void deleteConnectOptions(int idContract) {
        contractDao.deleteConnectOptions(idContract);
        logger.info("Connected options of contract " + idContract + " was deleted");
    }


    @Override
    @Transactional
    public void blockContract(int idContract) {
        ContractEntity contractEntity = getContract(idContract);
        contractEntity.setBlockedContract("Blocked");
        contractDao.update(contractEntity);
        logger.info("Contract " + idContract + " was blocked");
    }

    @Override
    @Transactional
    public void unblockContract(int idContract) {
        ContractEntity contractEntity = getContract(idContract);
        contractEntity.setBlockedContract("Unblocked");
        contractDao.update(contractEntity);
        logger.info("Contract " + idContract + " was unblocked");
    }

    @Override
    @Transactional
    public void addContract(ContractEntity contractEntity, TariffEntity tariffEntity, ClientEntity clientEntity) {
        contractEntity.setTariff(tariffEntity);
        contractEntity.setBlockedContract("Unblocked");
        contractEntity.setAdminBlock("N");
        contractEntity.setClientEntity(clientEntity);
        contractDao.addContract(contractEntity);
        logger.info("Contract " + contractEntity.getIdContract() + " was added");
    }

    @Override
    @Transactional
    public List<ContractEntity> list() {
        logger.info("List contracts was obtained");
        return contractDao.list();
    }

    @Override
    @Transactional
    public List<ContractEntity> listById(int idClient) {
        logger.info("List of contracts by clientID " + idClient + " was obtained");
        return contractDao.listById(idClient);
    }

    @Override
    @Transactional
    public void removeContract(int idContract) {
        contractDao.removeContract(idContract);
        logger.info("Contract " + idContract + " was removed");
    }
}
