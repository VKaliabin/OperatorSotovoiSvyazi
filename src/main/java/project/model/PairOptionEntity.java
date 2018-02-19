package project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pair_option")
public class PairOptionEntity {
    @Id
    @Column(name = "idPAIR_OPTION")
    private int idPairOption;


    public int getIdPairOption() {
        return idPairOption;
    }

    public void setIdPairOption(int idPairOption) {
        this.idPairOption = idPairOption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PairOptionEntity that = (PairOptionEntity) o;

        if (idPairOption != that.idPairOption) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idPairOption;
    }
}
