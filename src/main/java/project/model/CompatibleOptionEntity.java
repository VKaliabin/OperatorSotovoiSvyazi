package project.model;

import javax.persistence.*;

@Entity
@Table(name = "compatible_option")
public class CompatibleOptionEntity {
    @Id
    @Column(name = "idCOMPATIBLE_OPTION")
    private int idCompatibleOption;

//    @OneToOne(optional = false, cascade = CascadeType.ALL)
//    @JoinColumn(name = "idTARIFF")
//    private TariffEntity tariff;

//    @ManyToOne(optional=false, cascade=CascadeType.ALL)
//    @JoinColumn (name="idOPTION")
//    private OptionEntity optionEntity;

//    public OptionEntity getOption() {
//        return optionEntity;
//    }
//
//    public void setOption(OptionEntity option) {
//        this.optionEntity = option;
//    }

//    public TariffEntity getTariff() {
//        return tariff;
//    }
//
//    public void setTariff(TariffEntity tariff) {
//        this.tariff = tariff;
//    }

    public int getIdCompatibleOption() {
        return idCompatibleOption;
    }

    public void setIdCompatibleOption(int idCompatibleOption) {
        this.idCompatibleOption = idCompatibleOption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompatibleOptionEntity that = (CompatibleOptionEntity) o;

        if (idCompatibleOption != that.idCompatibleOption) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idCompatibleOption;
    }
}
