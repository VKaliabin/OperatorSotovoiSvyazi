package project.RabbitMQ;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.stereotype.Component;
import project.model.OptionEntity;
import project.model.TariffEntity;
import project.service.api.OptionService;
import project.service.api.TariffService;
import project.utils.EjbModel;
import project.utils.OptionModel;
import project.utils.TariffModel;

import java.util.ArrayList;
import java.util.List;

@Component
public class MQSentModel {
    private static final Logger logger = LoggerFactory.getLogger(MQSentModel.class);
    @Autowired
    private TariffService tariffService;
    @Autowired
    private OptionService optionService;

    public void sendModel() {

        String queueName = "banner";
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(RabbitConfiguration.class);
        ctx.refresh();
        System.out.println("---Message is being sent---");


        EjbModel ejbModel = new EjbModel();
        List<TariffModel> tariffs = new ArrayList<>();
        for (TariffEntity ejbTarif : tariffService.listTariffs()) {
            TariffModel tariffModel = new TariffModel();
            tariffModel.setIdTariff(ejbTarif.getIdTariff());
            tariffModel.setNameTariff(ejbTarif.getNameTariff());
            tariffModel.setPriceTariff(ejbTarif.getPriceTariff());

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

            tariffModel.setOptionModels(options);
            tariffs.add(tariffModel);
        }
        ejbModel.setTariffs(tariffs);

        Gson gson = new Gson();
        String json = gson.toJson(ejbModel);
        RabbitTemplate rabbitTemplate = (RabbitTemplate) ctx.getBean("rabbitTemplate");

        rabbitTemplate.convertAndSend(queueName, json);

        logger.info("Message was send to banner ");
        ctx.close();

    }
}
