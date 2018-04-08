package project.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "contract")
public class ContractEntity {
    @Id
    @Column(name = "idCONTRACT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContract;

    @Column(name = "CONTRACT_NUMBER")
    private String contractNumber;

    @Column(name = "BLOCKED_CONTRACT")
    private String blockedContract;

    @Column(name = "ADMIN_BLOCK")
    private String adminBlock;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "connected_options", joinColumns = @JoinColumn(name = "idContract"),
            inverseJoinColumns = @JoinColumn(name = "idOption"))
    private List<OptionEntity> options;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idTARIFF")
    private TariffEntity tariff;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idCLIENT")
    private ClientEntity clientEntity;

    public ContractEntity() {
    }

    public List<OptionEntity> getOptions() {
        return options;
    }

    public void setOptions(List<OptionEntity> options) {
        this.options = options;
    }


    public int getIdContract() {
        return idContract;
    }

    public void setIdContract(int idContract) {
        this.idContract = idContract;
    }


    public void setTariff(TariffEntity tariff) {
        this.tariff = tariff;
    }

    public TariffEntity getTariff() {
        return tariff;
    }


    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }


    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }


    public String getBlockedContract() {
        return blockedContract;
    }

    public void setBlockedContract(String blockedContract) {
        this.blockedContract = blockedContract;
    }


    public String getAdminBlock() {
        return adminBlock;
    }

    public void setAdminBlock(String adminBlock) {
        this.adminBlock = adminBlock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractEntity that = (ContractEntity) o;
        return idContract == that.idContract &&
                Objects.equals(contractNumber, that.contractNumber) &&
                Objects.equals(blockedContract, that.blockedContract) &&
                Objects.equals(adminBlock, that.adminBlock) &&
                Objects.equals(options, that.options) &&
                Objects.equals(tariff, that.tariff) &&
                Objects.equals(clientEntity, that.clientEntity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idContract, contractNumber, blockedContract, adminBlock, options, tariff, clientEntity);
    }
}
