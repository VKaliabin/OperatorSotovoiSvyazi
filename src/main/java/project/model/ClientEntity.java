package project.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "client")
public class ClientEntity {
    @Id
    @Column(name = "idCLIENT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClient;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "PASSPORT_DATA")
    private String passportData;

    @Column(name = "ADRESS")
    private String adress;

    @Column(name = "EMAIL")
    private String emailOfEmail;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EXISTING_CLIENT")
    private Byte existingClient;

    @OneToMany (mappedBy="clientEntity", fetch=FetchType.EAGER)
    private Set<ContractEntity> contracts;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "idUSER"),
            inverseJoinColumns = @JoinColumn(name = "idROLE"))
    private Set<RoleEntity> roles;

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    @Transient
    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public ClientEntity(){}

    public Set<ContractEntity> getContracts(){return contracts;}


    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public String getPassportData() {
        return passportData;
    }

    public void setPassportData(String passportData) {
        this.passportData = passportData;
    }


    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }


    public String getEmailOfEmail() {
        return emailOfEmail;
    }

    public void setEmailOfEmail(String emailOfEmail) {
        this.emailOfEmail = emailOfEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Byte getExistingClient() {
        return existingClient;
    }

    public void setExistingClient(Byte existingClient) {
        this.existingClient = existingClient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientEntity that = (ClientEntity) o;

        if (idClient != that.idClient) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(that.dateOfBirth) : that.dateOfBirth != null) return false;
        if (passportData != null ? !passportData.equals(that.passportData) : that.passportData != null) return false;
        if (adress != null ? !adress.equals(that.adress) : that.adress != null) return false;
        if (emailOfEmail != null ? !emailOfEmail.equals(that.emailOfEmail) : that.emailOfEmail != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (existingClient != null ? !existingClient.equals(that.existingClient) : that.existingClient != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idClient;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (passportData != null ? passportData.hashCode() : 0);
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        result = 31 * result + (emailOfEmail != null ? emailOfEmail.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (existingClient != null ? existingClient.hashCode() : 0);
        return result;
    }
}
