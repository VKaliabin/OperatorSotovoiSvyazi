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
    public ContractEntity getContract(int idClient) {
//       return hibernateUtil.findByEmail();
       return null;
    }


    @Override
    public List<OptionEntity> getOptionsByContractId(int idContract) {
        String query = "select op.* from connected_options co, options op where co.idContract = :id and  co.idOption = op.idOPTION";
        return hibernateUtil.fetchAllById(query, idContract);
    }
}
