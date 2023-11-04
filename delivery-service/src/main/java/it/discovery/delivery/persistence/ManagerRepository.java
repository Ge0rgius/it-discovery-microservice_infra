package it.discovery.delivery.persistence;

import it.discovery.delivery.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {

}
