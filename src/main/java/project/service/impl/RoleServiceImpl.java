package project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.RoleDao;
import project.model.RoleEntity;
import project.service.api.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional
    public RoleEntity getRole(int idRole) {
        return roleDao.getRole(idRole);
    }
}
