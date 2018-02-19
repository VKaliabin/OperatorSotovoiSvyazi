package project.model;

import javax.persistence.*;

@Entity
@Table(name = "option")
public class OptionEntity {
    @Id
    @Column(name = "idOPTION")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOption;
    @Basic
    @Column(name = "NAME_OPTION")
    private String nameOption;
    @Basic
    @Column(name = "PRICE_OPTION")
    private int priceOption;
    @Basic
    @Column(name = "CONNECTION_COST_OPTION")
    private int connectionCostOption;


    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="idTARIFF")
    private TariffEntity tariff;

    @OneToOne (optional=false, mappedBy="option")
    private IncompatibleOptionEntity incOption;


    public int getIdOption() {
        return idOption;
    }

    public void setIdOption(int idOption) {
        this.idOption = idOption;
    }


    public String getNameOption() {
        return nameOption;
    }

    public void setNameOption(String nameOption) {
        this.nameOption = nameOption;
    }


    public int getPriceOption() {
        return priceOption;
    }

    public void setPriceOption(int priceOption) {
        this.priceOption = priceOption;
    }


    public int getConnectionCostOption() {
        return connectionCostOption;
    }

    public void setConnectionCostOption(int connectionCostOption) {
        this.connectionCostOption = connectionCostOption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OptionEntity that = (OptionEntity) o;

        if (idOption != that.idOption) return false;
        if (priceOption != that.priceOption) return false;
        if (connectionCostOption != that.connectionCostOption) return false;
        if (nameOption != null ? !nameOption.equals(that.nameOption) : that.nameOption != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOption;
        result = 31 * result + (nameOption != null ? nameOption.hashCode() : 0);
        result = 31 * result + priceOption;
        result = 31 * result + connectionCostOption;
        return result;
    }
}
