package it.discovery.payment.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Payment extends BaseEntity {

    private int orderId;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private PaymentProvider provider;

    private boolean success;

    private String transactionId;

    private double amount;

}
