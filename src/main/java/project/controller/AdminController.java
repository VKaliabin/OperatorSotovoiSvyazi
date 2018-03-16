package project.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import project.utils.SelectedOptionsModel;
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


    private String authentication(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user != null) {
            model.addAttribute("user", user.getUsername());
        } else {
            return "login";
        }
        return "";
    }

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String admin(Model model) {
        authentication(model);

        model.addAttribute("clients", clientService.listClients());
        model.addAttribute("role", roleService.getRole(2));
        return "admin/admin";
    }

    @RequestMapping(value = "/tariffs_admin", method = RequestMethod.GET)
    public String listTariffs(Model model) {
        authentication(model);

        List<TariffEntity> tariffEntities = tariffService.listTariffs();
        model.addAttribute("tariffs", tariffEntities);
        return "admin/tariffs_admin";
    }

    @RequestMapping(value = "/addnewtariff", method = RequestMethod.GET)
    public String newTariff(Model model) {
        authentication(model);

        model.addAttribute("tariff", new TariffEntity());
        return "admin/new_tariff";
    }

    @RequestMapping(value = "/new_tariff", method = RequestMethod.POST)
    public String addTariff(@ModelAttribute("tariff") TariffEntity tariff, Model model, BindingResult bindingResult) {
        authentication(model);
        tariffValidatorImpl.validate(tariff, bindingResult, tariffService.listTariffs());
        if (bindingResult.hasErrors()) {
            return "admin/new_tariff";
        }
        tariffService.add(tariff);

        return "redirect:/tariffs_admin";
    }

    @RequestMapping(value = "/delete_tariff", method = RequestMethod.GET)
    public String deleteTariff(Model model, @RequestParam(value = "id") int idTariff) {
        authentication(model);
        tariffService.remove(idTariff);
        return "redirect:/tariffs_admin";
    }


    @RequestMapping(value = "/edit_tariff", method = RequestMethod.GET)
    public String editTariff(Model model, @RequestParam(value = "id") int idTariff) {
        authentication(model);
        model.addAttribute("tariff", tariffService.getTariff(idTariff));
        return "admin/edit_tariff";
    }

    @RequestMapping(value = "/edit_nameAndprice_tariff", method = RequestMethod.POST)
    public String editNameandPriceTariff(Model model, @RequestParam("nameTariff") String nameTariff,
                                         @RequestParam("priceTariff") int priceTariff,
                                         @RequestParam("idTariff") int idTariff) {
        authentication(model);
        TariffEntity tariffEntity = tariffService.getTariff(idTariff);
        if (nameTariff.length() == 0) {
            tariffEntity.setNameTariff(tariffService.getTariff(idTariff).getNameTariff());
        } else {
            tariffEntity.setNameTariff(nameTariff);
        }
        tariffEntity.setPriceTariff(priceTariff);
        tariffService.update(tariffEntity);
        return "redirect:/tariffs_admin";
    }

    @RequestMapping(value = "/options_admin", method = RequestMethod.GET)
    public String listOptions(Model model) {
        authentication(model);

        List<OptionEntity> optionEntities = optionService.listAllOptions();
        model.addAttribute("options", optionEntities);
        return "admin/options_admin";
    }

    @RequestMapping(value = "/addnewoption", method = RequestMethod.GET)
    String newOptions(Model model) {
        authentication(model);

        model.addAttribute("tariffs", tariffService.listTariffs());
        model.addAttribute("option", new OptionEntity());
        return "new_option";
    }

    @RequestMapping(value = "/new_option", method = RequestMethod.POST)
    public String addOption(@ModelAttribute("option") OptionEntity option, Model model,
                            @RequestParam("typeOption") String typeOption,
                            @RequestParam("id") int idTariff, BindingResult bindingResult) {
        authentication(model);

        List<OptionEntity> list = optionService.listAllOptions();
        optionValidatorImpl.validate(option, bindingResult, list);
        if (bindingResult.hasErrors()) {
            model.addAttribute("tariffs", tariffService.listTariffs());
            return "admin/new_option";
        }
        if (idTariff != 0) {
            TariffEntity tariffEntity = tariffService.getTariff(idTariff);
            option.setTariff(tariffEntity);
            option.setCompatibility(typeOption);
            optionService.addOption(option);
        }
        return "redirect:/options_admin";
    }

    @RequestMapping(value = "/edit_option", method = RequestMethod.GET)
    public String editOption(Model model, @RequestParam("id") int idOption) {
        authentication(model);

        model.addAttribute("option", optionService.getOption(idOption));
        model.addAttribute("tariffs", tariffService.listTariffs());
        return "admin/edit_option";
    }

    @RequestMapping(value = "/edit_nameAndprice_option", method = RequestMethod.POST)
    public String editNameandPriceOption(Model model, @RequestParam("idOption") int idOption,
                                         @RequestParam("optionName") String optionName,
                                         @RequestParam("optionPrice") Integer optionPrice,
                                         @RequestParam("optionCost") Integer optionCost,
                                         @RequestParam("id") Integer idTariff,
                                         @RequestParam("typeOption") String typeOption) {
        authentication(model);
        OptionEntity optionEntity = optionService.getOption(idOption);
        optionEntity.setTariff(tariffService.getTariff(idTariff));
        optionEntity.setConnectionCostOption(optionCost);
        if (optionName.length() == 0) {
            optionEntity.setNameOption(optionService.getOption(idOption).getNameOption());
        } else {
            optionEntity.setNameOption(optionName);
        }
        optionEntity.setPriceOption(optionPrice);
        if (typeOption.length() == 0) {
            optionEntity.setCompatibility(optionService.getOption(idOption).getCompatibility());
        } else {
            optionEntity.setCompatibility(typeOption);
        }
        optionService.update(optionEntity);
        return "redirect:/options_admin";
    }

    @RequestMapping(value = "/delete_option", method = RequestMethod.GET)
    public String deleteOption(Model model, @RequestParam("id") int idOption) {
        authentication(model);

        optionService.deleteOption(idOption);
        return "redirect:/options_admin";
    }

    @RequestMapping(value = "/contracts", method = RequestMethod.GET)
    public String contracts(Model model) {
        authentication(model);

        model.addAttribute("clients", clientService.listClients());
        return "admin/contracts_admin";
    }

    @RequestMapping(value = "/new_contract", method = RequestMethod.GET)
    public String newContract(Model model, @RequestParam("id") int idClient) {
        authentication(model);

        model.addAttribute("tariffs", tariffService.listTariffs());
        model.addAttribute("client", clientService.getClientId(idClient));
        model.addAttribute("contract", new ContractEntity());
        return "admin/new_contract";
    }

    @RequestMapping(value = "/addContract", method = RequestMethod.POST)
    public String addContract(Model model, @ModelAttribute("contract") ContractEntity contract, BindingResult bindingResult,
                              @RequestParam("id") int idTariff,
                              @RequestParam("idClient") int idClient) {
        authentication(model);
        model.addAttribute("tariffs", tariffService.listTariffs());
        List<ContractEntity> list = contractService.list();
        contractValidatorImpl.validate(contract, bindingResult, list);
        if (bindingResult.hasErrors()) {
            model.addAttribute("tariffs", tariffService.listTariffs());
            model.addAttribute("client", clientService.getClientId(idClient));
            model.addAttribute("contract", new ContractEntity());
            return "admin/new_contract";
        }
        if (idTariff != 0) {
            TariffEntity tariffEntity = tariffService.getTariff(idTariff);
            contract.setTariff(tariffEntity);
            contract.setBlockedContract("Unblocked");
            contract.setAdminBlock("N");
            contract.setClientEntity(clientService.getClientId(idClient));
            logger.debug("contract = {}", contract.toString());
            contractService.addContract(contract);
        }
        return "redirect:/contracts";
    }

    @RequestMapping(value = "/unblock_client", method = RequestMethod.GET)
    public String unblockClient(Model model, @RequestParam("id") int idClient) {
        authentication(model);

        ClientEntity clientEntity = clientService.getClientId(idClient);
        clientEntity.setExistingClient("Unblocked");
        clientService.updateClient(clientEntity);
        model.addAttribute("clients", clientService.listClients());
        model.addAttribute("role", roleService.getRole(2));
        return "admin/admin";
    }

    @RequestMapping(value = "/block_client", method = RequestMethod.GET)
    public String blockClient(Model model, @RequestParam("id") int idClient) {
        authentication(model);

        ClientEntity clientEntity = clientService.getClientId(idClient);
        clientEntity.setExistingClient("Blocked");
        clientService.updateClient(clientEntity);
        model.addAttribute("clients", clientService.listClients());
        model.addAttribute("role", roleService.getRole(2));
        return "admin/admin";
    }

    @RequestMapping(value = "/show_client", method = RequestMethod.GET)
    public String showDetatils(Model model, @RequestParam("id") int idClient) {
        authentication(model);

        model.addAttribute("tariffs", tariffService.listTariffs());
        model.addAttribute("client", clientService.getClientId(idClient));
        model.addAttribute("contracts", contractService.listById(idClient));

        return "admin/detail_client";
    }

    @RequestMapping(value = "/block_contract", method = RequestMethod.GET)
    public String blockingContract(Model model, @RequestParam("id") int idContract,
                                   @RequestParam("idClient") int idClient) {
        authentication(model);
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
        authentication(model);
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
        return "admin/search_result";
    }

    @RequestMapping(value = "/changetariff", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    String changetariff(@RequestParam("tariffId") int tariffId,
                        @RequestParam("contractId") int contractId) {
        List<OptionEntity> optionEntities = optionService.listOptions(tariffId);
        List<OptionEntity> connectedOptionsList = contractService.getContract(contractId).getOptions();

        List<SelectedOptionsModel> selected = optionService.getOptions(optionEntities, connectedOptionsList);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
        try {
            json = ow.writeValueAsString(selected);
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
}