package project.dao;



import org.springframework.stereotype.Repository;
import project.model.RoleEntity;



public interface RoleDao{

        public RoleEntity getRole(int idRole);

}
