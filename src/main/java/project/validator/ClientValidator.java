package project.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import project.model.ClientEntity;
import project.service.ClientService;
@Component
public class ClientValidator implements Validator {
    @Autowired
    private ClientService clientService;

    @Override
    public boolean supports(Class<?> aClass) {
        return ClientEntity.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
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
