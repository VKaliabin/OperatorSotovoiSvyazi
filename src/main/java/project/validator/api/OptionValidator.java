package project.validator.api;

import org.springframework.validation.Errors;
import project.model.OptionEntity;

import java.util.List;

public interface OptionValidator {

    void validate(Object var1, Errors var2, List<OptionEntity> list);
}
