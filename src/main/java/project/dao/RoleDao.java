package project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import project.model.RoleEntity;

public interface RoleDao extends JpaRepository<RoleEntity, Integer> {
}
