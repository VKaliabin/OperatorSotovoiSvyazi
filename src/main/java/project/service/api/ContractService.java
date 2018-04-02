package project.service.api;

import project.model.ClientEntity;
import project.model.ContractEntity;
import project.model.TariffEntity;

import java.util.List;

public interface ContractService {

     ContractEntity getContract(int idContract);

     ContractEntity update(ContractEntity contract);

     void deleteConnectOptions(int idContract);

     void blockContract(int idClient);

     void unblockContract(int idClient);

     void addContract(ContractEntity contractEntity, TariffEntity tariffEntity, ClientEntity clientEntity);

     List<ContractEntity> list();

    List<ContractEntity> listById(int idClient);
}
