package it.discovery.payment.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Customer extends BaseEntity {
    private String name;

    private String cardNumber;

    private double balance;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private PaymentProvider provider;

    @OneToMany
    private List<Payment> payments;
}
