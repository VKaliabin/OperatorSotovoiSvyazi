package project.utils;

import project.model.ClientEntity;
import project.model.RoleEntity;
import java.util.List;

public class SearchModel {
    private String userName;
    private List<ClientEntity> clientEntities;
    private RoleEntity role;

    public SearchModel(String userName, List<ClientEntity> clientEntities, RoleEntity role) {
        this.userName = userName;
        this.clientEntities = clientEntities;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ClientEntity> getClientEntities() {
        return clientEntities;
    }

    public void setClientEntities(List<ClientEntity> clientEntities) {
        this.clientEntities = clientEntities;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "SearchModel{" +
                "userName='" + userName + '\'' +
                ", clientEntities=" + clientEntities +
                ", role=" + role +
                '}';
    }
}
