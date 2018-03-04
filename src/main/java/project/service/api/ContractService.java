package project.service.api;

import project.model.ContractEntity;
import java.util.List;

public interface ContractService {

     ContractEntity getContract(int idContract);

     ContractEntity update(ContractEntity contract);

     void deleteConnectOptions(int idContract);

     void addContract(ContractEntity contractEntity);

     List<ContractEntity> list();

    List<ContractEntity> listById(int idClient);
}
