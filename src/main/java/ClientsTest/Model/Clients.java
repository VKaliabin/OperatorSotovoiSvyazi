package ClientsTest.Model;

import javax.persistence.*;

@Entity(name = "clients")

public class Clients {

    @Id
    @Column(name = "id_clients")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(name = "Name")
    public String name;
    @Column(name = "Age")
    public int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Client{" + "id_clients=" + id + "name-" + name + "age-" + age + "}";
    }
}
