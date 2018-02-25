package project.constroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.model.ClientEntity;
import project.service.ClientService;
import project.service.SecurityService;
import project.validator.ClientValidator;

@Controller
public class LoginController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private ClientValidator clientValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new ClientEntity());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") ClientEntity userForm, BindingResult bindingResult, Model model) {
        clientValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {return "registration";}
        clientService.addClient(userForm);
        securityService.autoLogin(userForm.getEmailOfEmail(), userForm.getConfirmPassword());
        return "redirect:/welcome";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        return "login";
    }
}
