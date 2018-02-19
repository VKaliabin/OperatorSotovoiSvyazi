package project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "compatible_option")
public class CompatibleOptionEntity {
    @Id
    @Column(name = "idCOMPATIBLE_OPTION")
    private int idCompatibleOption;


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
