package project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.model.ClientEntity;
import project.model.ContractEntity;
import project.model.OptionEntity;
import project.model.TariffEntity;
import project.service.api.ClientService;
import project.service.api.ContractService;
import project.service.api.OptionService;
import project.service.api.TariffService;
import project.utils.ContractModel;

import java.util.List;

@Controller
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private TariffService tariffService;
    @Autowired
    private OptionService optionService;


    @RequestMapping(value = {"/","/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user.getUsername());

        ClientEntity clientEntity = clientService.findByEMail(user.getUsername());
        model.addAttribute("contracts", clientEntity.getContracts());
        if (clientEntity.getExistingClient().equals("Blocked")) {
            return "blocked_user";
        } else {
            return "welcome";
        }
    }


    @RequestMapping(value = "/block", method = RequestMethod.GET)
    public String block(Model model, @RequestParam(value = "id") int id) {
        ContractEntity contractEntity = contractService.getContract(id);
        contractEntity.setBlockedContract("Blocked");
        contractService.update(contractEntity);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user.getUsername());

        ClientEntity clientEntity = clientService.findByEMail(user.getUsername());
        model.addAttribute("contracts", clientEntity.getContracts());
        return "welcome";
    }

    @RequestMapping(value = "/unblock", method = RequestMethod.GET)
    public String unblock(Model model, @RequestParam(value = "id") int id) {
        logger.debug("contract id ={}", id);
        ContractEntity contractEntity = contractService.getContract(id);
        contractEntity.setBlockedContract("Unblocked");
        contractService.update(contractEntity);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user.getUsername());

        ClientEntity clientEntity = clientService.findByEMail(user.getUsername());
        model.addAttribute("contracts", clientEntity.getContracts());
        return "welcome";
    }

    @RequestMapping(value = "/tariffs_user", method = RequestMethod.GET)
    public String tariffs(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user.getUsername());

        ClientEntity clientEntity = clientService.findByEMail(user.getUsername());
        model.addAttribute("contracts", clientEntity.getContracts());

        List<TariffEntity> tarifs = tariffService.listTariffs();
        model.addAttribute("tariffs", tarifs);
        return "tariffs_user";
    }

    @RequestMapping(value = "/show_tariff", method = RequestMethod.GET)
    public String showTariff(Model model, @RequestParam(value = "id") int id,
                             @RequestParam(value = "currentTar") int currentTar,
                             @RequestParam(value = "idContract") int idContract) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user.getUsername());

        TariffEntity tariffEntity = tariffService.getTariff(id);
        model.addAttribute("tariff", tariffEntity);
        model.addAttribute("currentTariff", currentTar);

        List<OptionEntity> optionEntities = optionService.listOptions(id);
        model.addAttribute("options", optionEntities);

        List<OptionEntity> currentOptions = contractService.getContract(idContract).getOptions();
        model.addAttribute("connectedOptions", currentOptions);
        ContractEntity contractEntity = contractService.getContract(idContract);
        model.addAttribute("contractEntity", contractEntity);
        model.addAttribute("contract", new ContractModel());
        return "options_user";
    }


}
