package project.utils;

import java.util.List;

public class AllOptionsModel {
    private List<OptionModel> optionEntities;
    private List<OptionModel> connectedOptions;

    public AllOptionsModel(List<OptionModel> optionEntities, List<OptionModel> connectedOptions) {
        this.optionEntities = optionEntities;
        this.connectedOptions = connectedOptions;
    }

    public List<OptionModel> getOptionEntities() {
        return optionEntities;
    }

    public void setOptionEntities(List<OptionModel> optionEntities) {
        this.optionEntities = optionEntities;
    }

    public List<OptionModel> getConnectedOptions() {
        return connectedOptions;
    }

    public void setConnectedOptions(List<OptionModel> connectedOptions) {
        this.connectedOptions = connectedOptions;
    }
}
