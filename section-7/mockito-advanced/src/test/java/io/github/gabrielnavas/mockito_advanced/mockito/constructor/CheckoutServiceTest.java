package io.github.gabrielnavas.mockito_advanced.mockito.constructor;

import io.github.gabrielnavas.mockito_advanced.service.CheckoutService;
import io.github.gabrielnavas.mockito_advanced.service.PaymentProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

public class CheckoutServiceTest {

    @DisplayName("Display name")
    @Test
    public void testMockObjectConstruction() {
        // Given
        try (MockedConstruction<PaymentProcessor> mocked = mockConstruction(
                PaymentProcessor.class,
                (mock, context) -> when(mock.chargeCustomer(anyString(), any(BigDecimal.class)))
                        .thenReturn(BigDecimal.TEN)
        )) {
            CheckoutService checkoutService = new CheckoutService();

            // When
            BigDecimal result = checkoutService.purchaseProduct("MacBook Pro", "42");

            // Then
            assertEquals(BigDecimal.TEN, result);
            assertEquals(1, mocked.constructed().size());
        }
        // When
        // Then
    }
}
