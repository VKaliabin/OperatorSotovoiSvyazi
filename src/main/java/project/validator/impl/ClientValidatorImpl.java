package project.validator.impl;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import project.model.ClientEntity;
import project.service.api.ClientService;
import project.validator.api.ClientValidator;

@Component
public class ClientValidatorImpl implements ClientValidator {


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

    }
}
