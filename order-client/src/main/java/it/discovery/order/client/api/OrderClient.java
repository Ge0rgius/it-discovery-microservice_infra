package it.discovery.order.client.api;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import it.discovery.order.client.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class OrderClient implements OrderFacade {

    private final String baseUrl;

    private final RetryConfig retryConfig;

    public OrderClient(String baseUrl, RestTemplate restTemplate) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
        retryConfig = RetryConfig.custom()
                .maxAttempts(3)
                .retryExceptions(IOException.class, RestClientException.class)
                .waitDuration(Duration.ofMillis(200))
                .build();
    }

    private final RestTemplate restTemplate;

    @Override
    public List<OrderDTO> findAll() {
        //TODO
        return null;
    }

    @Override
    public Optional<OrderDTO> findOne(int orderId) {
        try {
            Retry retry = Retry.of("dev", retryConfig);
            return retry.executeSupplier(() -> Optional.ofNullable(restTemplate.
                    getForObject(STR. "\{ baseUrl }/orders/\{ orderId }" , OrderDTO.class)));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return Optional.empty();
        }
    }
}
