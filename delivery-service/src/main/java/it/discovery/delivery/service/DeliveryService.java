package it.discovery.delivery.service;

import event.IntegrationEvent;
import event.shipment.ShipmentSucceededEvent;
import it.discovery.delivery.domain.Manager;
import it.discovery.delivery.domain.Shipment;
import it.discovery.delivery.persistence.ManagerRepository;
import it.discovery.delivery.persistence.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final ManagerRepository managerRepository;

    private final ShipmentRepository shipmentRepository;

    private final KafkaTemplate<Integer, IntegrationEvent> kafkaTemplate;

    public void deliver(int orderId) {
        //TODO find all non-busy managers
        List<Manager> managers = managerRepository.findAll();
        Manager deliveryManager = managers.get(0);

        Shipment shipment = shipmentRepository.findByOrderId(orderId).orElse(new Shipment());

        shipment.setOrderId(orderId);
        shipment.setDelivered(true);
        shipment.setDeliveryDate(LocalDateTime.now());
        shipment.setDeliveryManager(deliveryManager);

        shipmentRepository.save(shipment);

        kafkaTemplate.send("shipments", shipment.getId(), new ShipmentSucceededEvent(orderId));

//		for(OrderItem item : order.getItems()) {
//			Book book = item.getBook();
//			book.setAmount(book.getAmount() - item.getNumber());
//			bookRepository.save(book);
//		}
//
//		Notification notification = new Notification();
//		notification.setEmail(order.getCustomer().getEmail());
//		notification.setRecipient(order.getCustomer().getName());
//		notification.setTitle("Order " + order.getId() + " is delivered");
//		notification.setText("Hi/n. Your order has been delivered");
//
//		notificationService.sendNotification(notification);
//
//		System.out.println("Order delivered!");
    }

}
