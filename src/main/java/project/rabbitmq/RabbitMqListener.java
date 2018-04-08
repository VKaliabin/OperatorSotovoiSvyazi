package project.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class RabbitMqListener {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqListener.class);
    /**
     * It send the object in form JSON to queue
     *
     * @see MQSentModel#sendModel()
     */
    @Autowired
    private MQSentModel mqSentModel;

    /**
     * This method send the JSON to queue if messages has come from another queue
     *
     * @param message - message which has come from queue "request"
     */
    @RabbitListener(queues = "request")
    public void processQueue1(Message message) {
        String recMessage = new String(message.getBody());
        logger.info("Received message from request: " + recMessage);
        if (recMessage.equals("Send the model")) {
            try {
                mqSentModel.sendModel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


