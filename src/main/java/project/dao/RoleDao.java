package project.dao;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import project.model.RoleEntity;



public interface RoleDao extends JpaRepository<RoleEntity,Integer>{



}
