package project.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.dao.api.ContractDao;
import project.exception.DAOexception;
import project.hibernate.HibernateUtil;
import project.model.ContractEntity;

import java.io.Serializable;
import java.util.List;

@Repository
public class ContractDaoImpl implements ContractDao {
    /**
     * this variable is responsible for database work
     *
     * @see HibernateUtil
     */
    @Autowired
    private HibernateUtil hibernateUtil;

    /**
     * This method get a contract by id of this one
     *
     * @param idContcract - id of a contract
     * @return - return a contract(ContractEntity) found by id
     * @see HibernateUtil#fetchById(Serializable, Class)
     */
    @Override
    public ContractEntity getContract(int idContcract) {
        return hibernateUtil.fetchById(idContcract, ContractEntity.class);
    }

    /**
     * This method update a contract
     *
     * @param contract - required contract(ContractEntity) for update
     * @return - return updated contract
     * @see HibernateUtil#update(Object)
     */
    @Override
    public ContractEntity update(ContractEntity contract) {
        return hibernateUtil.update(contract);
    }

    /**
     * This method delete options(relationships) which are connected with this contract
     * but do not delete from database
     *
     * @param idContract - ID of required contract
     * @see HibernateUtil#deleteConnecOption(String, int)
     */
    @Override
    public void deleteConnectOptions(int idContract) {
        String query = "delete from connected_options where idContract=:id";
        try {
            hibernateUtil.deleteConnecOption(query, idContract);
        } catch (DAOexception daOexception) {
            daOexception.printStackTrace();
        }
    }

    /**
     * This method add a contract(ContractEntity)
     *
     * @param contractEntity - new contract which is added
     * @see HibernateUtil#add(Object)
     */
    @Override
    public void addContract(ContractEntity contractEntity) {
        hibernateUtil.add(contractEntity);
    }

    /**
     * This method get a list of all contracts
     *
     * @return - return list of all contracts
     * @see HibernateUtil#fetchAll(Class)
     */
    @Override
    public List<ContractEntity> list() {
        List<ContractEntity> contractEntityList = null;
        try {
            contractEntityList = hibernateUtil.fetchAll(ContractEntity.class);
        } catch (DAOexception daOexception) {
            daOexception.printStackTrace();
        }
        return contractEntityList;
    }

    /**
     * This method get a list of contracts which are connected with one client
     *
     * @param idClient - ID required client
     * @return - return contracts list of a client by client ID
     * @see HibernateUtil#fetchAllById(String, Class, int)
     */
    @Override
    public List<ContractEntity> listById(int idClient) {
        String query = "select * from contract where idCLIENT=:id";
        List<ContractEntity> contracts = null;
        try {
            contracts = hibernateUtil.fetchAllById(query, ContractEntity.class, idClient);
        } catch (DAOexception daOexception) {
            daOexception.printStackTrace();
        }
        return contracts;
    }

    /**
     * This method delete a contract by contract ID
     *
     * @param idContract - id of a required contract
     * @see HibernateUtil#delete(Serializable, Class)
     */
    @Override
    public void removeContract(int idContract) {
        hibernateUtil.delete(idContract, ContractEntity.class);
    }
}
