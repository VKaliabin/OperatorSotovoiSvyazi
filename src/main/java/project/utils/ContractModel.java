package project.utils;


import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ContractModel {
    private int idContract;
    private String contractNumber;
    private List<String> options;
    private int tariffId;

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

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getTariffId() {
        return tariffId;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    @Override
    public String toString() {
        return "ContractModel{" +
                "idContract=" + idContract +
                ", contractNumber='" + contractNumber + '\'' +
                ", options=" + options +
                ", tariffId=" + tariffId +
                '}';
    }
}
