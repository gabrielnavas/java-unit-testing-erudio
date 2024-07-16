package io.github.gabrielnavas.mockito_advanced.mockitoinline;

import io.github.gabrielnavas.mockito_advanced.entity.Order;
import io.github.gabrielnavas.mockito_advanced.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServiceTest {

    private final OrderService orderService = new OrderService();
    private final UUID defaultUuid = UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454");
    private final LocalDateTime defaultLocalDateTime = LocalDateTime.of(2024, 7, 15, 22, 59, 59);

    @DisplayName("should Include Random Order Id When No orderId Exists")
    @Test
    public void test_ShouldIncludeRandomOrderId_When_noOrderIdExists() {
        // Given
        try (MockedStatic<UUID> mockedUuid = Mockito.mockStatic(UUID.class)) {
            mockedUuid.when(UUID::randomUUID).thenReturn(defaultUuid);

            // When
            Order order = orderService.createOrder("IOS 15", 1000L, null);

            // Then
            assertEquals(defaultUuid, order.getId());
        }

    }

    @DisplayName("should Include Random Order Id When No orderId Exists")
    @Test
    public void test_ShouldIncludeCurrentTime_When_CreateANewOrder() {
        // Given
        try (MockedStatic<LocalDateTime> mockedUuid = Mockito.mockStatic(LocalDateTime.class)) {
            mockedUuid.when(LocalDateTime::now).thenReturn(defaultLocalDateTime);

            // When
            Order order = orderService.createOrder("IOS 15", 1000L, null);

            // Then
            assertEquals(defaultLocalDateTime, order.getCreationDate());
        }

    }
}
