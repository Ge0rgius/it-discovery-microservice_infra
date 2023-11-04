package it.discovery.order;

import it.discovery.order.domain.Customer;
import it.discovery.order.persistence.CustomerRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner(
            CustomerRepository customerRepository) {
        return _ -> {

            Customer customer = new Customer();
            customer.setId(1);
            customer.setName("Sam Newman");
            customer.setEmail("sam.newman@gmail.com");
            customerRepository.save(customer);
        };
    }
}
