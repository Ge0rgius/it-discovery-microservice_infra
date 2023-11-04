package it.discovery.order.service;

import event.IntegrationEvent;
import event.order.OrderVerifiedEvent;
import it.discovery.order.domain.Order;
import it.discovery.order.domain.OrderItem;
import it.discovery.order.persistence.CustomerRepository;
import it.discovery.order.persistence.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final KafkaTemplate<Integer, IntegrationEvent> kafkaTemplate;

    public void proceed(int orderId) {
        orderRepository.findById(orderId).ifPresent(order -> {
            order.setCompleted(true);
            orderRepository.save(order);

            //FIXME avoid orderId duplication
            kafkaTemplate.send("orders", orderId, new OrderVerifiedEvent(orderId));

//            paymentService.pay(order);
//
//            Notification notification = new Notification();
//            notification.setEmail(order.getCustomer().getEmail());
//            notification.setRecipient(order.getCustomer().getName());
//            notification.setTitle("Order " + order.getId() + " is completed");
//            notification.setText("Hi/n. Your order has been completed");
//
//            notificationService.sendNotification(notification);
        });
    }

    public void cancel(int orderId) {
        orderRepository.findById(orderId).ifPresent(order -> {
            order.setCancelled(true);
            orderRepository.save(order);

//            Notification notification = new Notification();
//            notification.setEmail(order.getCustomer().getEmail());
//            notification.setRecipient(order.getCustomer().getName());
//            notification.setTitle("Order " + order.getId() + " is canceled");
//            notification.setText("Hi/n. Your order has been canceled");
//
//            notificationService.sendNotification(notification);

        });
    }

    public Order createOrder(int bookId, int number, int customerId, double price) {
        Order order = new Order();
        order.addItem(new OrderItem(bookId, number, price));
        order.setCustomer(customerRepository.findById(customerId).orElseThrow());

        orderRepository.save(order);

        return order;
    }

    public void addBook(int orderId, int bookId, int number, double price) {
        orderRepository.findById(orderId).ifPresent(order -> {
            order.addItem(new OrderItem(bookId, number, price));
            orderRepository.save(order);
        });
    }

    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    public Order findOrderById(int orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }

}
