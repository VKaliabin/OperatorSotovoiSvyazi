package project.dao;

import project.model.ContractEntity;
import java.util.List;

public interface ContractDao {

    public ContractEntity getContract(int idContract);
    public ContractEntity update(ContractEntity contract);
    public void deleteConnectOptions(int idContract);
    public void addContract(ContractEntity contractEntity);
    public List<ContractEntity> list();
    List<ContractEntity> listById(int idClient);
}
