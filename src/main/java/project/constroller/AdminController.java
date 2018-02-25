package project.constroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import project.model.ClientEntity;
import project.model.TariffEntity;
import project.service.ClientService;
import project.service.TariffService;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private TariffService tariffService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user.getUsername());

        List<ClientEntity> listClients = clientService.listClients();
        model.addAttribute("clients", listClients);
        return "admin";
    }
    @RequestMapping(value = "/tariffs_admin", method = RequestMethod.GET)
    public String listTariffs(Model model){
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user.getUsername());

        List<TariffEntity> tariffEntities = tariffService.listTariffs();
        model.addAttribute("tariffs", tariffEntities);
        return "tariffs_admin";
    }
//    @RequestMapping(value = "/addnewtariff", method = RequestMethod.GET)
//    public String newTariff(Model model){
//        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
//        User user = (User) authentication.getPrincipal();
//        model.addAttribute("user", user.getUsername());
//
//
//    }

}
