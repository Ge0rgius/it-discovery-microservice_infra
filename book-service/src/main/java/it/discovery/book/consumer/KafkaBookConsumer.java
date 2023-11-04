package it.discovery.book.consumer;

import event.IntegrationEvent;
import event.shipment.ShipmentSucceededEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@EnableKafka
@Slf4j
public class KafkaBookConsumer {

    @KafkaListener(topics = "shipments")
    void handle(IntegrationEvent event) {
        log.error(STR. "Book service received event \{ event }" );
        if (event instanceof ShipmentSucceededEvent shipmentSucceededEvent) {
            // TODO reduce book amount
        }
    }
}
