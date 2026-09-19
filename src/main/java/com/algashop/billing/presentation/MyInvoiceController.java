package com.algashop.billing.presentation;

import com.algashop.billing.application.invoice.query.InvoiceOutput;
import com.algashop.billing.application.invoice.query.InvoiceQueryService;
import com.algashop.billing.application.security.SecurityChecks;
import com.algashop.billing.infrastructure.security.SecurityAnnotations;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers/me/orders/{orderId}/invoice")
@RequiredArgsConstructor
public class MyInvoiceController {

    private final InvoiceQueryService invoiceQueryService;
    private final SecurityChecks securityChecks;

    @GetMapping
    @SecurityAnnotations.CanReadMyInvoices
    public InvoiceOutput findByOrder(@PathVariable String orderId) {
        return invoiceQueryService.findByOrderIdAndCustomerId(orderId, securityChecks.getAuthenticatedUserId());
    }
}
