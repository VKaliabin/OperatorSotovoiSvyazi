package project.model;

import javax.persistence.*;

@Entity
@Table(name = "contract")
public class ContractEntity {
    @Id
    @Column(name = "idCONTRACT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContract;
    @Basic
    @Column(name = "CONTRACT_NUMBER")
    private String contractNumber;
    @Basic
    @Column(name = "BLOCKED_CONTRACT")
    private Byte blockedContract;


    @OneToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="idTARIFF")
    private TariffEntity tariff;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="idCLIENT")
    private ClientEntity clientEntity;


    public int getIdContract() {
        return idContract;
    }

    public void setIdContract(int idContract) {
        this.idContract = idContract;
    }


    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }


    public Byte getBlockedContract() {
        return blockedContract;
    }

    public void setBlockedContract(Byte blockedContract) {
        this.blockedContract = blockedContract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractEntity that = (ContractEntity) o;

        if (idContract != that.idContract) return false;
        if (contractNumber != null ? !contractNumber.equals(that.contractNumber) : that.contractNumber != null)
            return false;
        if (blockedContract != null ? !blockedContract.equals(that.blockedContract) : that.blockedContract != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idContract;
        result = 31 * result + (contractNumber != null ? contractNumber.hashCode() : 0);
        result = 31 * result + (blockedContract != null ? blockedContract.hashCode() : 0);
        return result;
    }
}
