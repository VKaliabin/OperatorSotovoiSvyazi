package project.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import project.model.ContractEntity;
import project.model.OptionEntity;
import project.service.api.ContractService;
import project.service.api.OptionService;
import project.service.api.TariffService;
import project.utils.ContractModel;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes(value = "contract")
public class CartController {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private ContractService contractService;
    @Autowired
    private TariffService tariffService;
    @Autowired
    private OptionService optionService;

    @ModelAttribute("contractt")
    public ContractModel contractCart() {
        return new ContractModel();
    }

    /*
        Display the cart
     */
    @RequestMapping(value = "/cart")
    public ModelAndView addContractCart(HttpSession session, @ModelAttribute("contractt") ContractModel contract) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        if (user != null) {
            modelAndView.addObject("user", user.getUsername());
        } else {
            modelAndView.setViewName("login&regist/login");
            return modelAndView;
        }
        modelAndView.addObject("user", user.getUsername());
        if (contract.getIdContract() != 0 || contract.getTariffEntity() != null) {
            Object object = session.getAttribute("contract");
            List<ContractModel> contractModelList = null;
            if (object != null) {
                contractModelList = (List<ContractModel>) object;
                boolean flag = false;
                for (ContractModel contractModel : contractModelList) {
                    if (contractModel.getIdContract() == contract.getIdContract()) {
                        contractModelList.remove(contractModel);
                        contract = getContractModel(contract);
                        contractModelList.add(contract);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    contract = getContractModel(contract);
                    contractModelList.add(contract);
                }
            } else {
                contractModelList = new ArrayList<>();
                contract = getContractModel(contract);
                contractModelList.add(contract);
            }
            modelAndView.addObject("contract", contractModelList);
        }
        modelAndView.setViewName("client/cart");
        return modelAndView;
    }


    /*
    Accept the changes
     */
    @RequestMapping(value = "/acceptCart")
    public ModelAndView addContractCart(HttpSession session, SessionStatus status) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        if (user != null) {
            modelAndView.addObject("user", user.getUsername());
        } else {
            modelAndView.setViewName("login&regist/login");
            return modelAndView;
        }
        modelAndView.addObject("user", user.getUsername());
        modelAndView.setViewName("redirect:/welcome");
        Object object = session.getAttribute("contract");

        List<ContractModel> contractModelList = null;
        if (object != null) {
            contractModelList = (List<ContractModel>) object;
            for (ContractModel contractModel : contractModelList) {
                if (contractModel.getIdContract() == 0) {
                    break;
                }
                ContractEntity newcontract = contractService.getContract(contractModel.getIdContract());

                newcontract.setTariff(tariffService.getTariff(contractModel.getTariffId()));
                if (contractModel.getOptions() != null) {
                    List<OptionEntity> optionEntities = new ArrayList<>();
                    for (Integer options : contractModel.getOptions()) {
                        optionEntities.add(optionService.getOption(options));
                    }
                    newcontract.setOptions(optionEntities);
                }
                contractService.update(newcontract);
            }
            status.setComplete();
        }

        return modelAndView;
    }

    /*
    Cancel he changes
     */
    @RequestMapping(value = "/deleteModel")
    public ModelAndView addContractCart(HttpSession session, @RequestParam("number") String contractNumber) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user.getUsername());
        modelAndView.setViewName("client/cart");
        Object object = session.getAttribute("contract");

        List<ContractModel> contractModelList = null;
        if (object != null) {
            contractModelList = (List<ContractModel>) object;
            for (ContractModel contractModel : contractModelList) {
                if (contractModel.getContractNumber().equals(contractNumber)) {
                    contractModelList.remove(contractModel);
                    break;
                }
            }
        }
        modelAndView.addObject("contract", contractModelList);
        return modelAndView;
    }

    private ContractModel getContractModel(ContractModel contractModel) {
        contractModel.setTariffEntity(tariffService.getTariff(contractModel.getTariffId()));
        if (contractModel.getOptions() != null) {
            List<OptionEntity> optionEntityList = new ArrayList<>();
            for (Integer optionId : contractModel.getOptions()) {
                optionEntityList.add(optionService.getOption(optionId));
            }
            contractModel.setOptionEntityList(optionEntityList);
        }
        return contractModel;
    }

}
