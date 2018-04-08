package project.utils;


import org.springframework.stereotype.Component;
import project.model.OptionEntity;
import project.model.TariffEntity;

import java.util.List;

@Component
public class ContractModel {

    private int idContract;
    private String contractNumber;
    private List<Integer> options;
    private int tariffId;
    private TariffEntity tariffEntity;
    private List<OptionEntity> optionEntityList;

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

    public List<Integer> getOptions() {
        return options;
    }

    public void setOptions(List<Integer> options) {
        this.options = options;
    }

    public int getTariffId() {
        return tariffId;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    public TariffEntity getTariffEntity() {
        return tariffEntity;
    }

    public void setTariffEntity(TariffEntity tariffEntity) {
        this.tariffEntity = tariffEntity;
    }

    public List<OptionEntity> getOptionEntityList() {
        return optionEntityList;
    }

    public void setOptionEntityList(List<OptionEntity> optionEntityList) {
        this.optionEntityList = optionEntityList;
    }


    @Override
    public String toString() {
        return "ContractModel{" +
                "idContract=" + idContract +
                ", contractNumber='" + contractNumber + '\'' +
                ", options=" + options +
                ", tariffId=" + tariffId +
                ", tariffEntity=" + tariffEntity +
                ", optionEntityList=" + optionEntityList +
                '}';
    }
}
