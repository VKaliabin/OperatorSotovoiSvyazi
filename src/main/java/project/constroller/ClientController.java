package project.constroller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.dao.ClientDao;
import project.model.ClientEntity;
import project.model.ContractEntity;
import project.model.OptionEntity;
import project.model.RoleEntity;
import project.service.ClientService;
import project.service.ContractService;
import project.service.SecurityService;
import project.service.SecurityServiceImpl;
import project.validator.ClientValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ContractService contractService;

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);


    @RequestMapping(value = {"/","/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user.getUsername());

        ClientEntity clientEntity = clientService.findByEMail(user.getUsername());
        model.addAttribute("contracts", clientEntity.getContracts());
        return "welcome";
    }


    @RequestMapping(value = "/block", method = RequestMethod.GET)
    public String block(Model model, @RequestParam(value = "id") int id) {
        logger.debug("contract id ={}", id );

        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user.getUsername());

        ClientEntity clientEntity = clientService.findByEMail(user.getUsername());
        ContractEntity contractEntity = contractService.getContract(id);

        contractEntity.setBlockedContract("Unblocked");
        model.addAttribute("contracts", clientEntity.getContracts());
        return "welcome";
    }

    @RequestMapping(value = "/unblock", method = RequestMethod.GET)
    public String unblock(Model model, @RequestParam(value = "id") int id) {
        logger.debug("contract id ={}", id );

        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user.getUsername());

        ClientEntity clientEntity = clientService.findByEMail(user.getUsername());
        ContractEntity contractEntity = contractService.getContract(id);

        contractEntity.setBlockedContract("Blocked");
        model.addAttribute("contracts", clientEntity.getContracts());
        return "welcome";
    }

}
