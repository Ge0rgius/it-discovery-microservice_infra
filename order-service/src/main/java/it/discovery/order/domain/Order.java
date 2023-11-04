package it.discovery.order.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity {
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @ManyToOne
    private Customer customer;

    private boolean payed;

    private boolean completed;

    private boolean cancelled;

    private boolean verified;

    public double getAmount() {
        return items.stream().mapToDouble(item -> item.getPrice() * item.getNumber()).sum();
    }

    public void addItem(OrderItem item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
    }


}
