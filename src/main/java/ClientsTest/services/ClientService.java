package ClientsTest.services;

import ClientsTest.Model.Clients;

import java.util.List;

public interface ClientService {
    public void addClient(Clients client);
    public void updateClient(Clients client);
    public void removeClient(int id);
    public Clients getClientId (int id);
    public List<Clients> listClients();
}
