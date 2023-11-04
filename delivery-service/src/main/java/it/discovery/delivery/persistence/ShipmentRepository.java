package it.discovery.delivery.persistence;

import it.discovery.delivery.domain.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {

    Optional<Shipment> findByOrderId(int shipmentId);
}
