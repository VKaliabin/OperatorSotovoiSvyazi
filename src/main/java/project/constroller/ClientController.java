package project.constroller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import project.model.ClientEntity;
import project.service.ClientService;
import project.service.SecurityService;
import project.service.SecurityServiceImpl;
import project.validator.ClientValidator;

@Controller
public class ClientController {


    @Autowired
    private ClientService clientService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private ClientValidator clientValidator;
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    @RequestMapping("addClient")
    public ModelAndView addClient(@ModelAttribute ClientEntity client){
        return new ModelAndView("index");
    }

    @RequestMapping("removeClient")
    public ModelAndView removeClient(@PathVariable("id") int id){
//        clientService.removeClient(id);
        return new ModelAndView();
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new ClientEntity());
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("userForm", new ClientEntity());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") ClientEntity userForm, BindingResult bindingResult, Model model) {

        logger.debug(userForm.toString());
//        System.out.println(userForm.toString());
        logger.debug("/////////////////////////////////////////////////");

        clientValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        clientService.save(userForm);

        securityService.autoLogin(userForm.geteMail(), userForm.getConfirmPassword());


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
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        return "login";
    }




    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin";
    }

}
