package project.model;

import javax.persistence.*;

@Entity
@Table(name = "incompatible_option")
public class IncompatibleOptionEntity {
    @Id
    @Column(name = "idINCOMPATIBLE_OPTION")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idIncompatibleOption;

//    @OneToOne(optional=false, cascade=CascadeType.ALL)
//    @JoinColumn (name="idOPTION")
//    private OptionEntity option;

//    @OneToOne(optional = false, cascade = CascadeType.ALL)
//    @JoinColumn(name = "idTARIFF")
//    private TariffEntity tariff;



//    public OptionEntity getOption() {
//        return option;
//    }
//
//    public void setOption(OptionEntity option) {
//        this.option = option;
//    }

//    public TariffEntity getTariff() {
//        return tariff;
//    }
//
//    public void setTariff(TariffEntity tariff) {
//        this.tariff = tariff;
//    }

    public int getIdIncompatibleOption() {
        return idIncompatibleOption;
    }

    public void setIdIncompatibleOption(int idIncompatibleOption) {
        this.idIncompatibleOption = idIncompatibleOption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IncompatibleOptionEntity that = (IncompatibleOptionEntity) o;

        if (idIncompatibleOption != that.idIncompatibleOption) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idIncompatibleOption;
    }
}
