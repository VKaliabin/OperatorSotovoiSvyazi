package project.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.dao.api.RoleDao;
import project.hibernate.HibernateUtil;
import project.model.RoleEntity;

import java.io.Serializable;

@Repository
public class RoleDaoImpl implements RoleDao {
    /**
     * this variable is responsible for database work
     *
     * @see HibernateUtil
     */
    @Autowired
    private HibernateUtil hibernateUtil;

    /**
     * This method get a role by id
     *
     * @param idRole - id of a required role
     * @return - return found role
     * @see HibernateUtil#fetchById(Serializable, Class)
     */
    @Override
    public RoleEntity getRole(int idRole) {
        return hibernateUtil.fetchById(idRole, RoleEntity.class);
    }
}
