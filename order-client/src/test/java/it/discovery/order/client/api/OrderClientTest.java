package it.discovery.order.client.api;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import it.discovery.order.client.dto.OrderDTO;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WireMockTest
class OrderClientTest {

    @RegisterExtension
    static WireMockExtension wm = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort()).build();

    OrderClient orderClient;

    @BeforeEach
    void setup() {
        orderClient = new OrderClient(wm.baseUrl());
    }

    @Test
    void findOne_orderExists_stubUsed() {
        final int orderId = 100;
        wm.stubFor(get(urlEqualTo(STR. "/orders/\{ orderId }" )).willReturn(aResponse()
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .withBody(STR. """
                        {
                            "id": \{ orderId },
                            "createdAt": "2023-10-22T08:50:29.4293769",
                            "customerId": 1,
                            "payed": false,
                            "completed": false,
                            "cancelled": false,
                            "amount": 5,
                            "price": 100
                        }
                        """ )));


        OrderDTO orderDTO = orderClient.findOne(orderId).orElseThrow();
        assertEquals(orderId, orderDTO.getId());

        wm.verify(1, getRequestedFor(urlMatching(STR. "/orders/\{ orderId }" )));
    }

    @Test
    void findOne_internalError_retryUsed() {
        final int orderId = 100;
        wm.stubFor(get(urlEqualTo(STR. "/orders/\{ orderId }" )).willReturn(aResponse()
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())));

        StopWatch stopWatch = StopWatch.createStarted();

        Optional<OrderDTO> orderDTO = orderClient.findOne(orderId);
        assertTrue(orderDTO.isEmpty());
        assertTrue(stopWatch.getTime(TimeUnit.MILLISECONDS) >= 400);

        wm.verify(3, getRequestedFor(urlMatching(STR. "/orders/\{ orderId }" )));
    }
}