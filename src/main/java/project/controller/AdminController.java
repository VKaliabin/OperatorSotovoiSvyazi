package project.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.model.ClientEntity;
import project.model.ContractEntity;
import project.model.OptionEntity;
import project.model.TariffEntity;
import project.service.api.*;
import project.utils.AllOptionsModel;
import project.utils.OptionModel;
import project.validator.impl.ContractValidatorImpl;
import project.validator.impl.OptionValidatorImpl;
import project.validator.impl.TariffValidatorImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;
    @Autowired
    private TariffService tariffService;
    @Autowired
    private OptionService optionService;
    @Autowired
    private OptionValidatorImpl optionValidatorImpl;
    @Autowired
    private ContractValidatorImpl contractValidatorImpl;
    @Autowired
    private ContractService contractService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private TariffValidatorImpl tariffValidatorImpl;


    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String admin(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ((!(authentication instanceof AnonymousAuthenticationToken)) && authentication != null) {
            User user = (User) authentication.getPrincipal();
            if (user != null) {
                model.addAttribute("user", user.getUsername());
            } else {
                return "login";
            }
        }
        model.addAttribute("clients", clientService.listClients());
        model.addAttribute("role", roleService.getRole(2));
        return "admin";
    }

    @RequestMapping(value = "/tariffs_admin", method = RequestMethod.GET)
    public String listTariffs(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();
        if (user != null) {
            model.addAttribute("user", user.getUsername());
        } else {
            return "login";

        }
        List<TariffEntity> tariffEntities = tariffService.listTariffs();
        model.addAttribute("tariffs", tariffEntities);
        return "tariffs_admin";
    }


    @RequestMapping(value = "/addnewtariff", method = RequestMethod.GET)
    public String newTariff(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user != null) {
            model.addAttribute("user", user.getUsername());
        } else {
            return "login";
        }

        model.addAttribute("tariff", new TariffEntity());
        return "new_tariff";
    }

    @RequestMapping(value = "/new_tariff", method = RequestMethod.POST)
    public String addTariff(@ModelAttribute("tariff") TariffEntity tariff, Model model, BindingResult bindingResult) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user != null) {
            model.addAttribute("user", user.getUsername());
        } else {
            return "login";
        }
        tariffValidatorImpl.validate(tariff, bindingResult, tariffService.listTariffs());
        if (bindingResult.hasErrors()) {
            model.addAttribute("tariff", new TariffEntity());
            return "new_tariff";
        }
        tariffService.add(tariff);

        return "redirect:/tariffs_admin";
    }

    @RequestMapping(value = "/delete_tariff", method = RequestMethod.GET)
    public String deleteTariff(Model model, @RequestParam(value = "id") int idTariff) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user != null) {
            model.addAttribute("user", user.getUsername());
        } else {
            return "login";
        }

        tariffService.remove(idTariff);
        return "redirect:/tariffs_admin";
    }


    @RequestMapping(value = "/edit_tariff", method = RequestMethod.GET)
    public String editTariff(Model model, @RequestParam(value = "id") int idTariff) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user != null) {
            model.addAttribute("user", user.getUsername());
        } else {
            return "login";
        }

        model.addAttribute("tariff", tariffService.getTariff(idTariff));
        return "edit_tariff";
    }

    @RequestMapping(value = "/edit_nameAndprice_tariff", method = RequestMethod.POST)
    public String editNameandPriceTariff(Model model, @RequestParam("nameTariff") String nameTariff,
                                         @RequestParam("priceTariff") int priceTariff,
                                         @RequestParam("idTariff") int idTariff) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user != null) {
            model.addAttribute("user", user.getUsername());
        } else {
            return "login";
        }
        TariffEntity tariffEntity = tariffService.getTariff(idTariff);
        tariffEntity.setNameTariff(nameTariff);
        if (nameTariff == null) {
            tariffEntity.setNameTariff(tariffService.getTariff(idTariff).getNameTariff());
        }
        tariffEntity.setPriceTariff(priceTariff);
        tariffService.update(tariffEntity);
        return "redirect:/tariffs_admin";
    }

    @RequestMapping(value = "/options_admin", method = RequestMethod.GET)
    public String listOptions(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user != null) {
            model.addAttribute("user", user.getUsername());
        } else {
            return "login";
        }

        List<OptionEntity> optionEntities = optionService.listAllOptions();
        model.addAttribute("options", optionEntities);
        return "options_admin";
    }

    @RequestMapping(value = "/addnewoption", method = RequestMethod.GET)
    String newOptions(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user != null) {
            model.addAttribute("user", user.getUsername());
        } else {
            return "login";
        }

        model.addAttribute("tariffs", tariffService.listTariffs());
        model.addAttribute("option", new OptionEntity());
        return "new_option";
    }

    @RequestMapping(value = "/new_option", method = RequestMethod.POST)
    public String addOption(@ModelAttribute("option") OptionEntity option, Model model,
                            @RequestParam("id") int idTariff, BindingResult bindingResult) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user != null) {
            model.addAttribute("user", user.getUsername());
        } else {
            return "login";
        }
        List<OptionEntity> list = optionService.listAllOptions();
        optionValidatorImpl.validate(option, bindingResult, list);
        if (bindingResult.hasErrors()) {
            model.addAttribute("tariffs", tariffService.listTariffs());
            return "new_option";
        }

        if (idTariff != 0) {
            TariffEntity tariffEntity = tariffService.getTariff(idTariff);
            option.setTariff(tariffEntity);
            optionService.addOption(option);
        }

        return "redirect:/options_admin";
    }

    @RequestMapping(value = "/edit_option", method = RequestMethod.GET)
    public String editOption(Model model, @RequestParam("id") int idOption) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user != null) {
            model.addAttribute("user", user.getUsername());
        } else {
            return "login";
        }
        model.addAttribute("option", optionService.getOption(idOption));
        model.addAttribute("tariffs", tariffService.listTariffs());
        return "edit_option";
    }

    @RequestMapping(value = "/edit_nameAndprice_option", method = RequestMethod.POST)
    public String editNameandPriceOption(Model model, @RequestParam("idOption") int idOption,
                                         @RequestParam("optionName") String optionName,
                                         @RequestParam("optionPrice") Integer optionPrice,
                                         @RequestParam("optionCost") Integer optionCost,
                                         @RequestParam("id") Integer idTariff) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user != null) {
            model.addAttribute("user", user.getUsername());
        } else {
            return "login";
        }
        OptionEntity optionEntity = optionService.getOption(idOption);
        optionEntity.setTariff(tariffService.getTariff(idTariff));
        optionEntity.setConnectionCostOption(optionCost);
        optionEntity.setNameOption(optionName);
        if (optionName == null) {
            optionEntity.setNameOption(optionService.getOption(idOption).getNameOption());
        }
        optionEntity.setPriceOption(optionPrice);
        optionService.update(optionEntity);
        return "redirect:/options_admin";
    }

    @RequestMapping(value = "/delete_option", method = RequestMethod.GET)
    public String deleteOption(Model model, @RequestParam("id") int idOption) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user != null) {
            model.addAttribute("user", user.getUsername());
        } else {
            return "login";
        }

        optionService.deleteOption(idOption);
        return "redirect:/options_admin";
    }

    @RequestMapping(value = "/contracts", method = RequestMethod.GET)
    public String contracts(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user != null) {
            model.addAttribute("user", user.getUsername());
        } else {
            return "login";
        }

        model.addAttribute("clients", clientService.listClients());
        return "contracts_admin";
    }

    @RequestMapping(value = "/new_contract", method = RequestMethod.GET)
    public String newContract(Model model, @RequestParam("id") int idClient) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user != null) {
            model.addAttribute("user", user.getUsername());
        } else {
            return "login";
        }

        model.addAttribute("tariffs", tariffService.listTariffs());
        model.addAttribute("client", clientService.getClientId(idClient));
        model.addAttribute("contract", new ContractEntity());
        return "new_contract";
    }

    @RequestMapping(value = "/addContract", method = RequestMethod.POST)
    public String addContract(Model model, @ModelAttribute("contract") ContractEntity contract, BindingResult bindingResult,
                              @RequestParam("id") int idTariff,
                              @RequestParam("idClient") int idClient) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user != null) {
            model.addAttribute("user", user.getUsername());
        } else {
            return "login";
        }

        model.addAttribute("tariffs", tariffService.listTariffs());
        List<ContractEntity> list = contractService.list();
        contractValidatorImpl.validate(contract, bindingResult, list);
        if (bindingResult.hasErrors()) {

            return "new_contract";
        }
        if (idTariff != 0) {
            TariffEntity tariffEntity = tariffService.getTariff(idTariff);
            contract.setTariff(tariffEntity);
            contract.setBlockedContract("Unblocked");
            contract.setClientEntity(clientService.getClientId(idClient));
            logger.debug("contract = {}", contract.toString());
            contractService.addContract(contract);
        }
        return "redirect:/contracts";
    }

    @RequestMapping(value = "/unblock_client", method = RequestMethod.GET)
    public String unblockClient(Model model, @RequestParam("id") int idClient) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user != null) {
            model.addAttribute("user", user.getUsername());
        } else {
            return "login";
        }

        ClientEntity clientEntity = clientService.getClientId(idClient);
        clientEntity.setExistingClient("Unblocked");
        clientService.updateClient(clientEntity);
        model.addAttribute("clients", clientService.listClients());
        model.addAttribute("role", roleService.getRole(2));
        return "admin";
    }

    @RequestMapping(value = "/block_client", method = RequestMethod.GET)
    public String blockClient(Model model, @RequestParam("id") int idClient) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user != null) {
            model.addAttribute("user", user.getUsername());
        } else {
            return "login";
        }

        ClientEntity clientEntity = clientService.getClientId(idClient);
        clientEntity.setExistingClient("Blocked");
        clientService.updateClient(clientEntity);
        model.addAttribute("clients", clientService.listClients());
        model.addAttribute("role", roleService.getRole(2));
        return "admin";
    }

    @RequestMapping(value = "/show_client", method = RequestMethod.GET)
    public String showDetatils(Model model, @RequestParam("id") int idClient) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user != null) {
            model.addAttribute("user", user.getUsername());
        } else {
            return "login";
        }

        model.addAttribute("tariffs", tariffService.listTariffs());
        model.addAttribute("client", clientService.getClientId(idClient));
        model.addAttribute("contracts", contractService.listById(idClient));

        return "detail_client";
    }

    @RequestMapping(value = "/block_contract", method = RequestMethod.GET)
    public String blockingContract(Model model, @RequestParam("id") int idContract,
                                   @RequestParam("idClient") int idClient) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user != null) {
            model.addAttribute("user", user.getUsername());
        } else {
            return "login";
        }
        ContractEntity contract = contractService.getContract(idContract);
        if (contract.getAdminBlock().equals("N")) {
            contract.setAdminBlock("Y");
            contractService.update(contract);
            model.addAttribute("id", idClient);
            return "redirect:/show_client";
        } else {
            contract.setAdminBlock("N");
            contractService.update(contract);
            model.addAttribute("id", idClient);
            return "redirect:/show_client";
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchContract(Model model, @RequestParam("sea") String contractNumber) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user != null) {
            model.addAttribute("user", user.getUsername());
        } else {
            return "login";
        }
        List<ContractEntity> contractEntities = contractService.list();
        int idContact = 0;
        List<ContractEntity> searchcontract = new ArrayList<>();
        for (ContractEntity contractEntity : contractEntities) {
            if (contractEntity.getContractNumber().contains(contractNumber)) {
                idContact = contractEntity.getIdContract();
                searchcontract.add(contractService.getContract(idContact));
            }
        }
        model.addAttribute("contract", searchcontract);
        model.addAttribute("role", roleService.getRole(2));
        return "search_result";
    }

    @RequestMapping(value = "/changetariff", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    String changetariff(@RequestParam("tariffId") int tariffId,
                        @RequestParam("contractId") int contractId) {
        List<OptionEntity> optionEntities = optionService.listOptions(tariffId);
        List<OptionEntity> connectedOptionsList = contractService.getContract(contractId).getOptions();
        AllOptionsModel allOptionsModel = new AllOptionsModel(getOptionModel(optionEntities), getOptionModel(connectedOptionsList));
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
        try {
            json = ow.writeValueAsString(allOptionsModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping(value = "/changeContract", method = RequestMethod.POST)
    public String changeContract(Model model, @RequestParam("selector") int tariffId,
                                 @RequestParam(value = "checkbox", required = false) List<Integer> checkbox,
                                 @RequestParam("idContract") int idContract) {
        contractService.deleteConnectOptions(idContract);
        ContractEntity contractEntity = contractService.getContract(idContract);
        List<OptionEntity> listConOptions = new ArrayList<>();
        if (tariffId != contractEntity.getTariff().getIdTariff()) {
            contractEntity.setTariff(tariffService.getTariff(tariffId));
        }

        if (checkbox != null) {
            for (Integer aCheckboxValue : checkbox) {
                listConOptions.add(optionService.getOption(aCheckboxValue));
            }
        }

        contractEntity.setOptions(listConOptions);
        contractService.update(contractEntity);
        int idClient = contractService.getContract(idContract).getClientEntity().getIdClient();
        model.addAttribute("id", idClient);
        return "redirect:/show_client";
    }


    private List<OptionModel> getOptionModel(List<OptionEntity> optionEntities) {
        List<OptionModel> optionModelList = new ArrayList<>();
        for (OptionEntity optionEntity : optionEntities) {
            OptionModel optionModel = new OptionModel();
            optionModel.setIdOption(optionEntity.getIdOption());
            optionModel.setConnectionCostOption(optionEntity.getConnectionCostOption());
            optionModel.setNameOption(optionEntity.getNameOption());
            optionModel.setPriceOption(optionEntity.getPriceOption());
            optionModelList.add(optionModel);
        }
        return optionModelList;
    }


}