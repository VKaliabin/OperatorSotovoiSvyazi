package project.validator.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import project.model.TariffEntity;
import project.validator.api.TariffValidator;

import java.util.List;

@Component
public class TariffValidatorImpl implements TariffValidator {
    private static final Logger logger = LoggerFactory.getLogger(TariffValidatorImpl.class);

    /**
     * This method check an object for errors
     *
     * @param var1   - checked object
     * @param errors - some errors
     * @param list   - tariffs list
     */
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
        logger.info("Tariff validation is completed");
    }
}
