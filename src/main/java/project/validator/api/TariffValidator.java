package project.validator.api;

import org.springframework.validation.Errors;
import project.model.TariffEntity;

import java.util.List;

public interface TariffValidator {

    void validate(Object var1, Errors var2, List<TariffEntity> list);
}
