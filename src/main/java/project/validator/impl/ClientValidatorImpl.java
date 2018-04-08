package project.validator.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import project.model.ClientEntity;
import project.service.api.ClientService;
import project.validator.api.ClientValidator;

@Component
public class ClientValidatorImpl implements ClientValidator {
    private static final Logger logger = LoggerFactory.getLogger(ClientValidatorImpl.class);

    /**
     * This method check an object for errors
     *
     * @param o             - checked object
     * @param errors        - some errors
     * @param clientService - service for work with ClientEntity
     * @see project.service.impl.ClientServiceImpl
     */
    @Override
    public void validate(Object o, Errors errors, ClientService clientService) {
        ClientEntity client = (ClientEntity) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailOfEmail", "Required");

        if (clientService.findByEMail(client.getEmailOfEmail()) != null) {
            errors.rejectValue("emailOfEmail", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (client.getPassword().length() < 4) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!client.getConfirmPassword().equals(client.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
        logger.info("Client validation is completed");
    }
}
