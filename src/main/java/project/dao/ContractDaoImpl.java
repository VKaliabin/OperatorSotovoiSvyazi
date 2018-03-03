package project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.hibernate.HibernateUtil;
import project.model.ContractEntity;

import java.util.List;

@Repository
public class ContractDaoImpl implements ContractDao {
    @Autowired
    private HibernateUtil hibernateUtil;

    @Override
    public ContractEntity getContract(int idContcract) {
       return (ContractEntity) hibernateUtil.fetchById(idContcract, ContractEntity.class );
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
    public void addContract(ContractEntity contractEntity) {
        hibernateUtil.add(contractEntity);
    }

    @Override
    public List<ContractEntity> list() {
        return hibernateUtil.fetchAll(ContractEntity.class);
    }

    @Override
    public List<ContractEntity> listById(int idClient) {
        String query = "select * from contract where idCLIENT=:id";
        return hibernateUtil.fetchAllById(query, ContractEntity.class ,idClient);
    }
}
