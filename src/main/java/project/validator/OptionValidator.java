package project.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import project.model.OptionEntity;
import project.service.OptionService;
@Component
public class OptionValidator  implements Validator {

    @Autowired
    private OptionService optionService;
    @Override
    public boolean supports(Class<?> aClass) {
        return OptionEntity.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        OptionEntity optionEntity =(OptionEntity) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameOption", "Required");
        for (OptionEntity entity : optionService.listAllOptions()) {
            if (entity.getNameOption().equals(optionEntity.getNameOption())) {
                errors.rejectValue("nameOption", "Duplicate.option.optionName");
                break;
            }
        }

    }
}
