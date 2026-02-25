package com.algashop.billing.application.invoice.query;

import com.algashop.billing.application.invoice.AbstractApplicationIT;
import com.algashop.billing.domain.model.invoice.Invoice;
import com.algashop.billing.domain.model.invoice.InvoiceRepository;
import com.algashop.billing.domain.model.invoice.InvoiceTestDataBuilder;
import com.algashop.billing.domain.model.invoice.PaymentMethod;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class InvoiceQueryServiceIT extends AbstractApplicationIT {

    @Autowired
    private InvoiceQueryService invoiceQueryService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Test
    public void shouldFindByOrderId() {
        Invoice invoice = InvoiceTestDataBuilder.anInvoice().build();
        invoice.changePaymentSettings(PaymentMethod.GATEWAY_BALANCE, null);
        invoiceRepository.saveAndFlush(invoice);

        InvoiceOutput invoiceOutput = invoiceQueryService.findByOrderId(invoice.getOrderId());

        Assertions.assertThat(invoiceOutput.getId()).isEqualTo(invoice.getId());
    }

}