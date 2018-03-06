package project.validator.impl;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import project.model.TariffEntity;
import project.validator.api.TariffValidator;

import java.util.List;

@Component
public class TariffValidatorImpl implements TariffValidator {

    @Override
    public void validate(Object var1, Errors errors, List<TariffEntity> list) {
        TariffEntity tariffEntity = (TariffEntity) var1;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameTariff", "Required");
        for (TariffEntity entity : list) {
            if (entity.getNameTariff().equals(tariffEntity.getNameTariff())) {
                errors.rejectValue("nameTariff", "Duplicate.tariff.nameTariff");
                break;
            }
        }
    }
}
