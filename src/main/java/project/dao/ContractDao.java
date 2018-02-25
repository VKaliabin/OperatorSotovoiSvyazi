package project.dao;

import project.model.ContractEntity;
import project.model.OptionEntity;

import java.util.List;

public interface ContractDao {

    public ContractEntity getContract(int idClient);
    public ContractEntity update(ContractEntity contract);

    public void deleteConnectOptions(int idContract);
    public void addConnectOptions(int idContract, int idOption);
//    List<OptionEntity> getOptionsByContractId(int idContract);
}
