package project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.HibernateUtil;
import project.model.ClientEntity;
import project.model.ContractEntity;
import project.model.OptionEntity;

import java.util.List;

@Repository
public class ContractDaoImpl implements ContractDao {
    @Autowired
    private HibernateUtil hibernateUtil;

    @Override
    public ContractEntity getContract(int idContcract) {
       return (ContractEntity) hibernateUtil.fetchById(idContcract, ContractEntity.class );
//       return null;
    }

    @Override
    public ContractEntity update(ContractEntity contract) {
        return hibernateUtil.update(contract);
    }

    @Override
    public void deleteConnectOptions(int idContract) {
        String query = "delete from connected_options where idContract=:id";
        hibernateUtil.deleteConnecOption(query, idContract);
    }

    @Override
    public void addConnectOptions(int idContract, int idOption) {
        String query = "insert into connected_options (idContract, idOption) values (:idCon, :idOpt)";
        hibernateUtil.addConnectOption(query,idContract,idOption);
    }
}
