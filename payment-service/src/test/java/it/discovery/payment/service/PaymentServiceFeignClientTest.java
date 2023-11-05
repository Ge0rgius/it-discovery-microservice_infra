package it.discovery.payment.service;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import it.discovery.order.client.dto.OrderDTO;
import it.discovery.payment.domain.Payment;
import it.discovery.payment.persistence.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
//TODO use dynamic port. See OrderClientTest
@WireMockTest(httpPort = 8081)
@AutoConfigureJsonTesters
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, properties = "eureka.client.enabled:false")
class PaymentServiceFeignClientTest {

    @TestConfiguration
    static class TestConfig {
        @Bean
        ServiceInstanceListSupplier mockOrderListSupplier() {
            return new MockInstanceSupplier("order-service", 8081);
        }
    }

    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentRepository paymentRepository;

    @Container
    @ServiceConnection
    static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka"));

    @Autowired
    JacksonTester<OrderDTO> jacksonTester;

    @Test
    void pay_orderExists_success() throws IOException {
        int orderId = 100;
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orderId);
        orderDTO.setCustomerId(1);

        stubFor(get(urlEqualTo(STR. "/orders/\{ orderId }" )).willReturn(aResponse()
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .withBody(jacksonTester.write(orderDTO).getJson())));

        paymentService.pay(orderId);

        List<Payment> payments = paymentRepository.findAll();
        assertEquals(1, payments.size());
        assertEquals(orderId, payments.get(0).getOrderId());
    }
}