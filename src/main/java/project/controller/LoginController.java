package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.model.ClientEntity;
import project.service.api.ClientService;
import project.service.api.SecurityService;
import project.validator.impl.ClientValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    /**
     * Service providing the methods for work with clientEntity
     */
    @Autowired
    private ClientService clientService;
    /**
     * Service providing the methods for work with SpringSecurity
     */
    @Autowired
    private SecurityService securityService;
    /**
     * Service providing the methods for work with validation of client
     */
    @Autowired
    private ClientValidatorImpl clientValidatorImpl;

    /**
     * Moving to registration page
     *
     * @param model - some data
     * @return - "registration.jsp" page
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new ClientEntity());
        return "loginAndOther/registration";
    }

    /**
     * Registration a new user(creating new client)
     *
     * @param userForm      - new client which was created by filling form
     * @param bindingResult - it keeps some errors
     * @param model         - some data
     * @return - return url "welcome"
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") ClientEntity userForm, BindingResult bindingResult, Model model) {
        clientValidatorImpl.validate(userForm, bindingResult, clientService);
        if (bindingResult.hasErrors()) {
            return "loginAndOther/registration";
        }
        userForm.setExistingClient("Unblocked");
        clientService.addClient(userForm);
        securityService.autoLogin(userForm.getEmailOfEmail(), userForm.getConfirmPassword());
        return "redirect:/welcome";
    }

    /**
     * Moving to the main page - login
     *
     * @param model  - some data
     * @param error  - string keeping the message about an error
     * @param logout - string keeping the message about logged out
     * @return - return "login.jsp" page
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }
        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        return "loginAndOther/login";
    }

    /**
     * Logging out
     *
     * @param request  - servlet request
     * @param response - servlet response
     * @return - return url "login?logout"
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

}
