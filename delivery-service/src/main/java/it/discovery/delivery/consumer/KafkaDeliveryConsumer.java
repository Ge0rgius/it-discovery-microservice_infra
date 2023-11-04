package it.discovery.delivery.consumer;

import event.IntegrationEvent;
import event.payment.PaymentSuccessEvent;
import it.discovery.delivery.service.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@EnableKafka
@Slf4j
public class KafkaDeliveryConsumer {

    @Autowired
    private DeliveryService deliveryService;

    @KafkaListener(topics = "payments")
    void handle(IntegrationEvent event) {
        log.info(STR. "Delivery service received event: \{ event }" );

        if (event instanceof PaymentSuccessEvent successEvent) {
            deliveryService.deliver(successEvent.getOrderId());
        }
    }
}
