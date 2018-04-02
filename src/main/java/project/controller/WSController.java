package project.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;
import project.model.OptionEntity;
import project.model.TariffEntity;
import project.service.api.OptionService;
import project.service.api.TariffService;
import project.utils.EjbModel;
import project.utils.JsonUtil;
import project.utils.OptionModel;
import project.utils.TariffModel;


import java.util.ArrayList;
import java.util.List;

@RestController
public class WSController {
    private static final Logger logger = LoggerFactory.getLogger(WSController.class);

    @Autowired
    private TariffService tariffService;
    @Autowired
    private OptionService optionService;

    @RequestMapping(value = "/test", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String test() {
        return "Hello";
    }


    @RequestMapping(value = "/getAllparams", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
//    ResponseEntity<EjbModel> getAllparams(@RequestParam(value = "_csrf", required = false) String csrf) {
    String getAllparams() {
        logger.debug("getAllParams !!");

        EjbModel ejbModel = new EjbModel();
        List<TariffModel> tariffs = new ArrayList<>();
        for (TariffEntity ejbTarif : tariffService.listTariffs()) {
            TariffModel tariffModel = new TariffModel();
            tariffModel.setIdTariff(ejbTarif.getIdTariff());
            tariffModel.setNameTariff(ejbTarif.getNameTariff());
            tariffModel.setPriceTariff(ejbTarif.getPriceTariff());
            logger.debug("!!!!!{}", optionService.listOptions(ejbTarif.getIdTariff()));
            List<OptionModel> options = new ArrayList<>();
            for (OptionEntity ejbOption : optionService.listOptions(ejbTarif.getIdTariff())) {
                OptionModel optionModel = new OptionModel();
                optionModel.setIdOption(ejbOption.getIdOption());
                optionModel.setNameOption(ejbOption.getNameOption());
                optionModel.setPriceOption(ejbOption.getPriceOption());
                optionModel.setConnectionCostOption(ejbOption.getConnectionCostOption());
                optionModel.setCompatibility(ejbOption.getCompatibility());
                options.add(optionModel);
            }
            logger.debug("&&&&& {}", options.toString());
            tariffModel.setOptionModels(options);
            tariffs.add(tariffModel);
        }
        ejbModel.setTariffs(tariffs);

        JsonUtil json = new JsonUtil();
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8081/getAllparams";
//        String model = json.json(ejbModel);
//        ResponseEntity<EjbModel> entity = restTemplate.exchange(url,
//                HttpMethod.POST,
//                new HttpEntity<>(model), EjbModel.class);

        return json.json(ejbModel);
//        return entity;


    }
}
