package com.algashop.billing.infrastructure.payment.fastpay;

import com.algashop.billing.presentation.BadGatewayException;

public class FastpayPaymentCaptureFailed extends BadGatewayException {
    public FastpayPaymentCaptureFailed() {
    }

    public FastpayPaymentCaptureFailed(String message, Throwable cause) {
        super(message, cause);
    }

    public FastpayPaymentCaptureFailed(String message) {
        super(message);
    }
}