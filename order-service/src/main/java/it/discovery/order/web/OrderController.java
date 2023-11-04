package it.discovery.order.web;

import it.discovery.order.client.dto.CreateOrderDTO;
import it.discovery.order.client.dto.OrderDTO;
import it.discovery.order.domain.Order;
import it.discovery.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    private final ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    //TODO Create another API version to preserve backward-compatibility
    public OrderDTO createOrder(@RequestBody CreateOrderDTO orderDTO) {
        return toDTO(orderService.createOrder(orderDTO.bookId(),
                orderDTO.number(), orderDTO.customerId(), orderDTO.price()));
    }

    @PutMapping("{orderId}")
    public void addBook(@PathVariable int orderId, int bookId, int number, double price) {
        orderService.addBook(orderId, bookId, number, price);
    }

    @PostMapping("{orderId}")
    public void completeOrder(@PathVariable int orderId) {
        orderService.proceed(orderId);
    }

    @DeleteMapping("{orderId}")
    public void cancel(@PathVariable int orderId) {
        orderService.cancel(orderId);
    }

    @GetMapping
    public List<OrderDTO> findOrders() {
        return orderService.findOrders().stream().map(this::toDTO).toList();
    }

    @GetMapping("{orderId}")
    public OrderDTO findOrderById(@PathVariable int orderId) {
        return toDTO(orderService.findOrderById(orderId));
    }

    private OrderDTO toDTO(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }
}
