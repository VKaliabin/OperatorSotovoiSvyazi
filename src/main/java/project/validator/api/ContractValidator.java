package project.validator.api;

import org.springframework.validation.Errors;
import project.model.ContractEntity;

import java.util.List;

public interface ContractValidator {

    void validate(Object var1, Errors var2, List<ContractEntity> list);
}
