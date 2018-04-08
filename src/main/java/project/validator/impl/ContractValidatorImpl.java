package project.validator.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import project.model.ContractEntity;
import project.validator.api.ContractValidator;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContractValidatorImpl implements ContractValidator {
    private static final Logger logger = LoggerFactory.getLogger(ContractValidatorImpl.class);

    /**
     * This method check an object for errors
     *
     * @param o             - checked object
     * @param errors        - some errors
     * @param listContracts - contracts list
     */
    @Override
    public void validate(Object o, Errors errors, List<ContractEntity> listContracts) {
        ContractEntity contractEntity = (ContractEntity) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contractNumber", "Required");

        if (contractEntity.getContractNumber().length() != 11) {
            errors.rejectValue("contractNumber", "Size.contract.contractNumber");
        }
        List<String> list = new ArrayList<>();
        for (ContractEntity s : listContracts) {
            list.add(s.getContractNumber());
        }
        if (list.contains(contractEntity.getContractNumber())) {
            errors.rejectValue("contractNumber", "Dublicate.contract.contractNumber");
        }
        logger.info("Contract validation is completed");
    }
}
