package it.discovery.order.consumer;

import event.IntegrationEvent;
import event.payment.PaymentFailedEvent;
import event.payment.PaymentSuccessEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

@Configuration
@EnableKafka
@Slf4j
public class KafkaOrderConsumer {

    @KafkaListener(topics = "payments")
    void handle(@Payload IntegrationEvent event) {
        log.info(STR. "Order service received event: \{ event }" );

        switch (event) {
            case PaymentSuccessEvent successEvent -> {
            }
            case PaymentFailedEvent failedEvent -> {
            }
            default -> {
            }
        }
    }
}
