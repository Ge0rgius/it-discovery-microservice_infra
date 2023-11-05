package it.discovery.payment.service;

import event.IntegrationEvent;
import event.payment.PaymentSuccessEvent;
import it.discovery.order.client.api.OrderFacade;
import it.discovery.order.client.dto.OrderDTO;
import it.discovery.payment.domain.Payment;
import it.discovery.payment.persistence.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    //	private final OrderRepository orderRepository;
//
    private final PaymentGateway paymentGateway;
    //
//	private final NotificationService notificationService;
//
    private final PaymentRepository paymentRepository;

    private final OrderFacade orderFacade;

    private final KafkaTemplate<Integer, IntegrationEvent> kafkaTemplate;

    private final CircuitBreakerFactory circuitBreakerFactory;

    public void pay(int orderId) {
        Payment payment = findOrder(orderId).map(paymentGateway::charge).orElseThrow(() -> new
                IllegalArgumentException(STR. "No order found: \{ orderId }" ));
//		order.setPayed(true);
//		orderRepository.save(order);
//
        paymentRepository.save(payment);

        kafkaTemplate.send("payments", payment.getId(), new PaymentSuccessEvent(payment.getId(), orderId));

//		Notification notification = new Notification();
//		notification.setEmail(order.getCustomer().getEmail());
//		notification.setRecipient(order.getCustomer().getName());
//		notification.setTitle("Order " + order.getId() + " was payed");
//		notification.setText("Hi/n. Your order was payed successfully");
//
//		notificationService.sendNotification(notification);
//		System.out.println("Charging completed");
    }

    private Optional<OrderDTO> findOrder(int id) {
        return circuitBreakerFactory.create("order-service")
                .run(() -> orderFacade.findOne(id), ex -> {
                    //TODO think about exception chaining
                    log.error(ex.getMessage(), ex);
                    return Optional.empty();
                });
    }

}
