package project.utils;

import java.util.List;

public class CheckedOptions {

    private int tariffId;
    private int contractId;
    private List<String> optionList;


    public int getTariffId() {
        return tariffId;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public List<String> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<String> optionList) {
        this.optionList = optionList;
    }

    @Override
    public String toString() {
        return "CheckedOptions{" +
                "tariffId=" + tariffId +
                ", contractId=" + contractId +
                ", optionList=" + optionList +
                '}';
    }
}
