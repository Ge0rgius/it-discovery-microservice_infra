package it.discovery.order.client.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerDTO {
    private int id;

    private LocalDateTime createdAt;

    private String name;

    private String phone;

    private String email;
}
