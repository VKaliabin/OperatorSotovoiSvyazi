package project.utils;

public class SelectedOptionsModel {

    private int idOption;
    private boolean chacked;
    private boolean disable;
    private OptionModel optionModel;

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

    public OptionModel getOptionEntity() {
        return optionModel;
    }

    public void setOptionEntity(OptionModel optionModel) {
        this.optionModel = optionModel;
    }

    @Override
    public String toString() {
        return "SelectedOptionsModel{" +
                "idOption=" + idOption +
                ", chacked=" + chacked +
                ", disable=" + disable +
                ", optionEntity=" + optionModel +
                '}';
    }
}
