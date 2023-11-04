package it.discovery.delivery;

import it.discovery.delivery.domain.Manager;
import it.discovery.delivery.persistence.ManagerRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(ManagerRepository managerRepository) {
        return args -> {
            Manager manager = new Manager();
            manager.setName("Peter");
            manager.setPhone("123");
            managerRepository.save(manager);
        };
    }
}
