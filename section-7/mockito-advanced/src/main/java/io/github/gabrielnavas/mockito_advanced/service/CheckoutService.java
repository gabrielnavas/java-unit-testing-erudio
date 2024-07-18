package io.github.gabrielnavas.mockito_advanced.service;

import java.math.BigDecimal;

public class CheckoutService {

    public BigDecimal purchaseProduct(String productName, String customerId) {
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        return paymentProcessor.chargeCustomer(customerId, BigDecimal.TEN);
    }
}
