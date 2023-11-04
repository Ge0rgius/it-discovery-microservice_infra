package it.discovery.order.client.api;

import it.discovery.order.client.dto.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderFacade {

    List<OrderDTO> findAll();

    Optional<OrderDTO> findOne(int orderId);
}
