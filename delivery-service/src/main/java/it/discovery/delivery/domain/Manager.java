package it.discovery.delivery.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Manager extends BaseEntity {

    private String name;

    private String email;

    private String phone;

    @OneToMany(mappedBy = "deliveryManager")
    private List<Shipment> shipments;
}
