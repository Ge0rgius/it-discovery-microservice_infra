package it.discovery.order.client.api;

import it.discovery.order.client.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient("order-service")
public interface OrderFacade {

    @GetMapping
    List<OrderDTO> findAll();

    @GetMapping("{orderId}")
    Optional<OrderDTO> findOne(@PathVariable int orderId);
}
