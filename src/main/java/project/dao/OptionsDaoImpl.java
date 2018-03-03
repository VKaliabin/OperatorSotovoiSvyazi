package project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.hibernate.HibernateUtil;
import project.model.OptionEntity;
import java.util.List;

@Repository
public class OptionsDaoImpl implements OptionDao {
    @Autowired
    private HibernateUtil hibernateUtil;

    @Override
    public List<OptionEntity> listOptions(int id) {
        String query = "select * from options where idTARIFF=:id";
        return hibernateUtil.fetchAllById(query, OptionEntity.class ,id);
    }

    @Override
    public List<OptionEntity> listAllOptions() {
        return hibernateUtil.fetchAll(OptionEntity.class);
    }

    @Override
    public void addOption(OptionEntity optionEntity) {
        hibernateUtil.add(optionEntity);
    }

    @Override
    public OptionEntity update(OptionEntity optionEntity) {
        return hibernateUtil.update(optionEntity);
    }

    @Override
    public void deleteOption(int idOptoin) {
        hibernateUtil.delete(idOptoin, OptionEntity.class);
    }

    @Override
    public OptionEntity getOption(int idOption) {
        return hibernateUtil.fetchById(idOption, OptionEntity.class);
    }

}
