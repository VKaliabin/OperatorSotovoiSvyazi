package project.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import project.model.ContractEntity;
import project.service.api.ContractService;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContractValidator implements Validator {

    @Autowired
    ContractService contractService;

    @Override
    public boolean supports(Class<?> aClass) {
        return ContractEntity.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ContractEntity contractEntity = (ContractEntity) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contractNumber", "Required");

        if (contractEntity.getContractNumber().length() != 11) {
            errors.rejectValue("contractNumber", "Size.contract.contractNumber");
        }
        List<String> list = new ArrayList<>();
        for (ContractEntity s : contractService.list()) {
            list.add(s.getContractNumber());
        }
        if (list.contains(contractEntity.getContractNumber())) {
            errors.rejectValue("contractNumber", "Dublicate.contract.contractNumber");
        }
    }
}
