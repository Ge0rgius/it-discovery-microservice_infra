package it.discovery.payment.service;

import it.discovery.order.client.api.OrderFacade;
import it.discovery.order.client.dto.OrderDTO;
import it.discovery.payment.domain.Payment;
import it.discovery.payment.persistence.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PaymentServiceTest {

    @MockBean
    OrderFacade orderFacade;

    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentRepository paymentRepository;

    @Container
    KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka"))
            .withKraft();

    @Test
    void pay_orderExists_success() {
        int orderId = 100;
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orderId);

        BDDMockito.given(orderFacade.findOne(orderId)).willReturn(Optional.of(orderDTO));
        paymentService.pay(orderId);

        List<Payment> payments = paymentRepository.findAll();
        assertEquals(1, payments.size());
        assertEquals(orderId, payments.get(0).getOrderId());
    }
}