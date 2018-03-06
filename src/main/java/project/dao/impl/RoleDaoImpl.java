package project.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.dao.api.RoleDao;
import project.hibernate.HibernateUtil;
import project.model.RoleEntity;

@Repository
public class RoleDaoImpl implements RoleDao {
    @Autowired
    private HibernateUtil hibernateUtil;

    @Override
    public RoleEntity getRole(int idRole) {
        return hibernateUtil.fetchById(idRole, RoleEntity.class);
    }
}
