package project.validator.impl;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import project.model.OptionEntity;
import project.validator.api.OptionValidator;

import java.util.List;

@Component
public class OptionValidatorImpl implements OptionValidator {


    @Override
    public void validate(Object o, Errors errors, List<OptionEntity> list) {
        OptionEntity optionEntity = (OptionEntity) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameOption", "Required");
        for (OptionEntity entity : list) {
            if (entity.getNameOption().equals(optionEntity.getNameOption())) {
                errors.rejectValue("nameOption", "Duplicate.option.optionName");
                break;
            }
        }

    }
}
