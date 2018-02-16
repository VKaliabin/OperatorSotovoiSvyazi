package ClientsTest.dao;



import ClientsTest.Model.Clients;

import java.util.List;

public interface ClientDao {
    public void addClient(Clients client);
    public void updateClient(Clients client);
    public void removeClient(int id);
    public Clients getClientId (int id);
    public List<Clients> listClients();
}
