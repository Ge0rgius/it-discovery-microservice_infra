package it.discovery.delivery.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Customer extends BaseEntity {
    private String name;

    private String address;

    private String phone;

    private String email;

}
