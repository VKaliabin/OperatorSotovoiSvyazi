package project.utils;

import project.model.OptionEntity;

public class SelectedOptionsModel {
    private int idOption;
    private boolean chacked;
    private boolean disable;
    private OptionEntity optionEntity;

    public int getIdOption() {
        return idOption;
    }

    public void setIdOption(int idOption) {
        this.idOption = idOption;
    }

    public boolean isChacked() {
        return chacked;
    }

    public void setChacked(boolean chacked) {
        this.chacked = chacked;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public OptionEntity getOptionEntity() {
        return optionEntity;
    }

    public void setOptionEntity(OptionEntity optionEntity) {
        this.optionEntity = optionEntity;
    }

    @Override
    public String toString() {
        return "SelectedOptionsModel{" +
                "idOption=" + idOption +
                ", chacked=" + chacked +
                ", disable=" + disable +
                ", optionEntity=" + optionEntity +
                '}';
    }
}
