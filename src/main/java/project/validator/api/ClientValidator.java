package project.validator.api;

import org.springframework.validation.Errors;
import project.service.api.ClientService;


public interface ClientValidator {

    void validate(Object var1, Errors var2, ClientService clientService);
}
