package project.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import project.utils.ContractModel;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes(value = "contract")
public class CartController {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @ModelAttribute("contractt")
    public ContractModel contractCart() {
        return new ContractModel();
    }

    @RequestMapping(value = "/cart")
    public ModelAndView addContractCart(HttpSession session, @ModelAttribute("contractt") ContractModel contract) {
        logger.debug("contract ={}", contract.toString());
        Object object = session.getAttribute("contract");

        List<ContractModel> contractModelList = null;
        if (object != null) {
            contractModelList = (List<ContractModel>) object;
            boolean flag = false;
            for (ContractModel contractModel : contractModelList) {
                if (contractModel.getIdContract() == contract.getIdContract()) {
                    contractModelList.remove(contractModel);
                    contractModelList.add(contract);
                    flag = true;
                    break;
                }
            }
            if (!flag)
                contractModelList.add(contract);

            logger.debug("1. contract = {}", contractModelList);
        } else {
            contractModelList = new ArrayList<>();
            contractModelList.add(contract);
            logger.debug("2. contract = {}", contractModelList);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cart");
        modelAndView.addObject("contract", contractModelList);
        return modelAndView;
    }


}
