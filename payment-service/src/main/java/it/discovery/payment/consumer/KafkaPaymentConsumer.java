package it.discovery.payment.consumer;

import event.IntegrationEvent;
import event.order.OrderVerifiedEvent;
import it.discovery.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

@Configuration
@EnableKafka
@Slf4j
public class KafkaPaymentConsumer {

    @Autowired
    private PaymentService paymentService;

    @KafkaListener(topics = "orders")
    void handle(@Payload IntegrationEvent event) {
        log.info(STR. "Payment service received event: \{ event }" );

        //Pattern matching for switch
        //String templates
        //Unnamed variables
        switch (event) {
            case OrderVerifiedEvent _ -> paymentService.pay(event.getEntityId());
            default -> log.debug(STR. "Received event \{ event }" );
        }
    }
}
