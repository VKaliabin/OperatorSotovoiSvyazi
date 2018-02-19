package project.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import project.model.ClientEntity;
import project.service.ClientService;

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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "eMail", "Required");

//        if (user.getUsername().length() < 8 || user.getUsername().length() > 32) {
//            errors.rejectValue("eMail", "Size.userForm.username");
//        }
        if (clientService.findByClientEmail(client.geteMail()) != null) {
            errors.rejectValue("eMail", "Duplicate.userForm.username");
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
