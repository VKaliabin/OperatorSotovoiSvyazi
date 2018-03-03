package project.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @Column(name = "idROLES")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idRoles;

    @Column(name = "TYPE_USER")
    private String typeUser;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private List<ClientEntity> users;

    public List<ClientEntity> getUsers() {
        return users;
    }

    public void setUsers(List<ClientEntity> users) {
        this.users = users;
    }

    public RoleEntity() {
    }

    public int getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(int idRoles) {
        this.idRoles = idRoles;
    }


    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        if (idRoles != that.idRoles) return false;
        if (typeUser != null ? !typeUser.equals(that.typeUser) : that.typeUser != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRoles;
        result = 31 * result + (typeUser != null ? typeUser.hashCode() : 0);
        return result;
    }
}
