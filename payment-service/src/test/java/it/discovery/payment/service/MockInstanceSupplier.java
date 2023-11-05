package it.discovery.payment.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class MockInstanceSupplier implements ServiceInstanceListSupplier {

    private final String serviceId;

    private final int port;

    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(List.of(
                new DefaultServiceInstance(serviceId + "1", serviceId, "localhost", port, false)));
    }
}
