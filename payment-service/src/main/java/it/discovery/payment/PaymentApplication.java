package it.discovery.payment;

import it.discovery.order.client.api.OrderClient;
import it.discovery.order.client.api.OrderFacade;
import it.discovery.payment.domain.Customer;
import it.discovery.payment.domain.PaymentProvider;
import it.discovery.payment.persistence.CustomerRepository;
import it.discovery.payment.persistence.PaymentProviderRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    OrderFacade orderFacade(Environment env, RestTemplate restTemplate) {
        return new OrderClient("http://order-service", restTemplate);
    }

    @Bean
    ApplicationRunner applicationRunner(
            PaymentProviderRepository paymentProviderRepository, CustomerRepository customerRepository) {
        return args -> {
            PaymentProvider provider = new PaymentProvider();
            provider.setName("Stripe");
            paymentProviderRepository.save(provider);

            Customer customer = new Customer();
            customer.setName("Sam Newman");
            customer.setProvider(provider);
            customerRepository.save(customer);
        };
    }
}
