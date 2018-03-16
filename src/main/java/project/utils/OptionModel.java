package project.utils;

public class OptionModel {

    private int idOption;
    private String nameOption;
    private int priceOption;
    private int connectionCostOption;
    private String compatibility;

    public int getIdOption() {
        return idOption;
    }

    public void setIdOption(int idOption) {
        this.idOption = idOption;
    }

    public String getNameOption() {
        return nameOption;
    }

    public void setNameOption(String nameOption) {
        this.nameOption = nameOption;
    }

    public int getPriceOption() {
        return priceOption;
    }

    public void setPriceOption(int priceOption) {
        this.priceOption = priceOption;
    }

    public int getConnectionCostOption() {
        return connectionCostOption;
    }

    public void setConnectionCostOption(int connectionCostOption) {
        this.connectionCostOption = connectionCostOption;
    }

    public String getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
    }
}
