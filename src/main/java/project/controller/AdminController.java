package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.model.ClientEntity;
import project.model.OptionEntity;
import project.model.TariffEntity;
import project.service.ClientService;
import project.service.OptionService;
import project.service.TariffService;
import project.validator.OptionValidator;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private TariffService tariffService;
    @Autowired
    private OptionService optionService;

    @Autowired
    private OptionValidator optionValidator;


    @RequestMapping(value = {"/","/admin"}, method = RequestMethod.GET)
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


    @RequestMapping(value = "/addnewtariff", method = RequestMethod.GET)
    public String newTariff(Model model){
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user.getUsername());

        model.addAttribute("tariff", new TariffEntity());
        return "new_tariff";
    }

    @RequestMapping(value = "/new_tariff", method = RequestMethod.POST)
    public String addTariff(@ModelAttribute("tariff") TariffEntity tariff, Model model){
        tariffService.add(tariff);

        return "redirect:/tariffs_admin";
    }

    @RequestMapping(value = "/delete_tariff", method = RequestMethod.GET)
    public String deleteTariff(Model model, @RequestParam(value = "id") int idTariff){
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user.getUsername());

        tariffService.remove(idTariff);
        return "redirect:/tariffs_admin";
    }


    @RequestMapping(value = "/edit_tariff", method = RequestMethod.GET)
    public String editTariff(Model model, @RequestParam(value = "id") int idTariff){
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user.getUsername());

        model.addAttribute("idTariff", idTariff);
        return "edit_tariff";
    }

    @RequestMapping(value = "/options_admin", method = RequestMethod.GET)
    public String listOptions(Model model){
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user.getUsername());

        List<OptionEntity> optionEntities = optionService.listAllOptions();
        model.addAttribute("options", optionEntities);
        return "options_admin";
    }
    @RequestMapping(value = "/addnewoption", method = RequestMethod.GET)
    String newOptions(Model model){
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user.getUsername());
        model.addAttribute("tariffs", tariffService.listTariffs());
        model.addAttribute("option", new OptionEntity());
        return "new_option";
    }

    @RequestMapping(value = "/new_option", method = RequestMethod.POST)
                public String addOption(@ModelAttribute("option") OptionEntity option, Model model,
                                        @RequestParam("id") int idTariff, BindingResult bindingResult){
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user.getUsername());

        optionValidator.validate(option, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("tariffs", tariffService.listTariffs());
//            model.addAttribute("option", new OptionEntity());
            return "new_option";
        }

        if(idTariff != 0){
            TariffEntity tariffEntity = tariffService.getTariff(idTariff);
            option.setTariff(tariffEntity);
            optionService.addOption(option);
        }


            return "redirect:/options_admin";
    }

    @RequestMapping(value = "/delete_option", method = RequestMethod.GET)
    public String deleteOption(Model model, @RequestParam("id") int idOption){
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user.getUsername());

        optionService.deleteOption(idOption);
        return "redirect:/options_admin";
    }
}
