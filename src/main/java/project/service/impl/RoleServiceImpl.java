package project.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dao.api.RoleDao;
import project.model.RoleEntity;
import project.service.api.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional
    public RoleEntity getRole(int idRole) {
        logger.info("Role " + idRole + " was obtained");
        return roleDao.getRole(idRole);
    }
}
