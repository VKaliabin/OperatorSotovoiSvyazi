package project.controller;


import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import project.utils.SelectedOptionsModel;

import java.io.IOException;
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

    private ClientEntity authentication(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user.getUsername());
        ClientEntity clientEntity = clientService.findByEMail(user.getUsername());
        return clientEntity;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();
        logger.debug("ROLE = {}", role);
        if (role.contains("ROLE_USER")) {
            return "redirect:/welcome";
        } else if (role.contains("ROLE_ADMIN")) {
            return "redirect:/admin";
        }
        return "redirect:/admin";
    }

    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        ClientEntity clientEntity = authentication(model);

        model.addAttribute("contracts", clientEntity.getContracts());
        if (clientEntity.getExistingClient().equals("Blocked")) {
            return "client/blocked_user";
        } else {
            return "client/welcome";
        }
    }

    @RequestMapping(value = "/block", method = RequestMethod.GET)
    public String block(Model model, @RequestParam(value = "id") int id) {

        contractService.blockContract(id);
        ClientEntity clientEntity = authentication(model);

        model.addAttribute("contracts", clientEntity.getContracts());
        return "client/welcome";
    }

    @RequestMapping(value = "/unblock", method = RequestMethod.GET)
    public String unblock(Model model, @RequestParam(value = "id") int id) {

        contractService.unblockContract(id);
        ClientEntity clientEntity = authentication(model);

        model.addAttribute("contracts", clientEntity.getContracts());
        return "client/welcome";
    }

    @RequestMapping(value = "/tariffs_user", method = RequestMethod.GET)
    public String tariffs(Model model) {
        ClientEntity clientEntity = authentication(model);
        model.addAttribute("contracts", clientEntity.getContracts());

        List<TariffEntity> tarifs = tariffService.listTariffs();
        model.addAttribute("tariffs", tarifs);
        return "client/tariffs_user";
    }

    @RequestMapping(value = "/show_tariff", method = RequestMethod.GET)
    public String showTariff(Model model, @RequestParam(value = "id") int id,
                             @RequestParam(value = "currentTar") int currentTar,
                             @RequestParam(value = "idContract") int idContract) {
        ClientEntity clientEntity = authentication(model);

        TariffEntity tariffEntity = tariffService.getTariff(id);
        model.addAttribute("tariff", tariffEntity);
        model.addAttribute("currentTariff", currentTar);

        List<OptionEntity> optionEntities = optionService.listOptions(id);
        List<OptionEntity> currentOptions = contractService.getContract(idContract).getOptions();
        List<SelectedOptionsModel> selected = optionService.getOptions(optionEntities, currentOptions);
        model.addAttribute("options", selected);


        ContractEntity contractEntity = contractService.getContract(idContract);
        model.addAttribute("contractEntity", contractEntity);
        model.addAttribute("contract", new ContractModel());
        return "client/options_user";
    }

    @RequestMapping(value = "/optionsUser", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    String optionsUser(Model model, @RequestParam(value = "optionList") String optionList,
                       @RequestParam(value = "tariffId") int tariffId,
                       @RequestParam(value = "contractId") int contractId) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            List<String> checkedList = mapper.readValue(optionList, new TypeReference<List<String>>() {
            });
            List<OptionEntity> optionEntities = optionService.listOptions(tariffId);
            List<SelectedOptionsModel> selectedOptionsModels = optionService.getChangedOptions(optionEntities, checkedList);

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(selectedOptionsModels);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
