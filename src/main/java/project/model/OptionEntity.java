package project.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "options")
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

    @ManyToMany(mappedBy = "options")
    private List<ContractEntity> contracts;

    @ManyToOne (optional=false)
    @JoinColumn (name="idTARIFF")
    private TariffEntity tariff;
//
//    @OneToOne (optional=false, mappedBy="option")
//    private IncompatibleOptionEntity incOption;

//    @OneToMany (mappedBy="optionEntity", fetch=FetchType.EAGER)
//    private Set<CompatibleOptionEntity> comOption;
    public OptionEntity(){};

    public List<ContractEntity> getContracts() {
        return contracts;
    }

    public void setContracts(List<ContractEntity> contracts) {
        this.contracts = contracts;
    }


    public TariffEntity getTariff() {
        return tariff;
    }

    public void setTariff(TariffEntity tariff) {
        this.tariff = tariff;
    }

//    public IncompatibleOptionEntity getIncOption() {
//        return incOption;
//    }
//
//    public void setIncOption(IncompatibleOptionEntity incOption) {
//        this.incOption = incOption;
//    }

//    public Set<CompatibleOptionEntity> getComOption() {
//        return comOption;
//    }
//
//    public void setComOption(Set<CompatibleOptionEntity> comOption) {
//        this.comOption = comOption;
//    }

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
