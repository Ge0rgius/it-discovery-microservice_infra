package it.discovery.order.client.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDTO {
    private int id;

    private LocalDateTime createdAt;

    private int customerId;

    private boolean payed;

    private boolean completed;

    private boolean cancelled;

    //TODO check if correctly initialized by ModelMapper
    private double amount;
}
