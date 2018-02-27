package project.dao;

import project.model.ContractEntity;


public interface ContractDao {

    public ContractEntity getContract(int idClient);
    public ContractEntity update(ContractEntity contract);
    public void deleteConnectOptions(int idContract);
}
