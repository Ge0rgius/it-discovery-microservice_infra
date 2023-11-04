package it.discovery.order.client.dto;

public record CreateOrderDTO(int bookId, int number, int customerId, double price) {
}
