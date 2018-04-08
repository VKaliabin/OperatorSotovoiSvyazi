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

    /**
     * This method get a contract by id of this one
     *
     * @param idContract - id of a contract
     * @return - return a contract(ContractEntity) found by id
     */
    @Override
    @Transactional
    public ContractEntity getContract(int idContract) {
        logger.info("Contract " + idContract + " was obtained");
        return contractDao.getContract(idContract);
    }

    /**
     * This method update a contract
     *
     * @param contract - required contract(ContractEntity) for update
     * @return - return updated contract
     */
    @Override
    @Transactional
    public ContractEntity update(ContractEntity contract) {
        logger.info("Contract " + contract.getIdContract() + " was updated");
        return contractDao.update(contract);
    }

    /**
     * This method delete options(relationships) which are connected with this contract
     * but do not delete from database
     *
     * @param idContract - ID of required contract
     */
    @Override
    @Transactional
    public void deleteConnectOptions(int idContract) {
        contractDao.deleteConnectOptions(idContract);
        logger.info("Connected options of contract " + idContract + " was deleted");
    }

    /**
     * Blocking a contract by ID
     *
     * @param idContract - id some contract
     */
    @Override
    @Transactional
    public void blockContract(int idContract) {
        ContractEntity contractEntity = getContract(idContract);
        contractEntity.setBlockedContract("Blocked");
        contractDao.update(contractEntity);
        logger.info("Contract " + idContract + " was blocked");
    }

    /**
     * Unblocking a contract by ID
     *
     * @param idContract - id some contract
     */
    @Override
    @Transactional
    public void unblockContract(int idContract) {
        ContractEntity contractEntity = getContract(idContract);
        contractEntity.setBlockedContract("Unblocked");
        contractDao.update(contractEntity);
        logger.info("Contract " + idContract + " was unblocked");
    }

    /**
     * Create new contract with binding a tariff and a client
     *
     * @param contractEntity - new contract
     * @param tariffEntity   - some tariff
     * @param clientEntity   - some client
     */
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

    /**
     * This method get a list of all contracts
     *
     * @return - return list of all contracts
     */
    @Override
    @Transactional
    public List<ContractEntity> list() {
        logger.info("List contracts was obtained");
        return contractDao.list();
    }

    /**
     * This method get a list of contracts which are connected with one client
     *
     * @param idClient - ID required client
     * @return - return contracts list of a client by client ID
     */
    @Override
    @Transactional
    public List<ContractEntity> listById(int idClient) {
        logger.info("List of contracts by clientID " + idClient + " was obtained");
        return contractDao.listById(idClient);
    }

    /**
     * This method delete a contract by contract ID
     *
     * @param idContract - id of a required contract
     */
    @Override
    @Transactional
    public void removeContract(int idContract) {
        contractDao.removeContract(idContract);
        logger.info("Contract " + idContract + " was removed");
    }
}
