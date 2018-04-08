package project.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tariff")
public class TariffEntity {
    @Id
    @Column(name = "idTARIFF")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTariff;

    @Column(name = "NAME_TARIFF")
    private String nameTariff;

    @Column(name = "PRICE_TARIFF")
    private int priceTariff;

    @OneToMany(mappedBy = "tariff", fetch = FetchType.EAGER)
    private List<OptionEntity> options;

    @OneToMany(mappedBy = "tariff", fetch = FetchType.EAGER)
    private List<ContractEntity> contracts;

    public TariffEntity() {
    }

    public void setOptions(List<OptionEntity> options) {
        this.options = options;
    }

    public void setContracts(List<ContractEntity> contracts) {
        this.contracts = contracts;
    }


    public List<ContractEntity> getContracts() {
        return contracts;
    }

    public List<OptionEntity> getOptions() {
        return options;
    }


    public int getIdTariff() {
        return idTariff;
    }

    public void setIdTariff(int idTariff) {
        this.idTariff = idTariff;
    }


    public String getNameTariff() {
        return nameTariff;
    }

    public void setNameTariff(String nameTariff) {
        this.nameTariff = nameTariff;
    }


    public int getPriceTariff() {
        return priceTariff;
    }

    public void setPriceTariff(int priceTariff) {
        this.priceTariff = priceTariff;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TariffEntity that = (TariffEntity) o;

        if (idTariff != that.idTariff) return false;
        if (priceTariff != that.priceTariff) return false;
        if (nameTariff != null ? !nameTariff.equals(that.nameTariff) : that.nameTariff != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTariff;
        result = 31 * result + (nameTariff != null ? nameTariff.hashCode() : 0);
        result = 31 * result + priceTariff;
        return result;
    }
}
