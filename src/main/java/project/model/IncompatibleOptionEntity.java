package project.model;

import javax.persistence.*;

@Entity
@Table(name = "incompatible_option")
public class IncompatibleOptionEntity {
    @Id
    @Column(name = "idINCOMPATIBLE_OPTION")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idIncompatibleOption;

    @OneToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="idOPTION")
    private OptionEntity option;



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
