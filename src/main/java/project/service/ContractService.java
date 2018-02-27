package project.service;

import project.model.ContractEntity;

public interface ContractService {

    public ContractEntity getContract(int clientId);
    public ContractEntity update(ContractEntity contract);
    public void deleteConnectOptions(int idContract);

}
