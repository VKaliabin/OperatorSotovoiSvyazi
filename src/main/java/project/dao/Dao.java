package project.dao;



import java.util.List;

public interface Dao {
    public void deleteConnectOptions(int idContract);
    public <T> T findByEmailOfEmail(String email);
    public <T> void add( Class<T> entityClass);
    public  <T> T update(Class<T> entityClass);
    public <T> void remove(int id, Class<T> entityClass);
    public  <T> T get (int id, Class<T> entityClass);
    public <T> List <T> list(Class<T> entityClass);
    public <T> List <T> list(String query, Class<T> entityClass, int id);
}
