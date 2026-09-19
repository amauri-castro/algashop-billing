package com.algashop.billing.presentation;

import com.algashop.billing.application.creditcard.management.CreditCardManagementService;
import com.algashop.billing.application.creditcard.management.TokenizedCreditCardInput;
import com.algashop.billing.application.creditcard.query.CreditCardOutput;
import com.algashop.billing.application.creditcard.query.CreditCardQueryService;
import com.algashop.billing.application.security.SecurityChecks;
import com.algashop.billing.infrastructure.security.SecurityAnnotations;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers/me/credit-cards")
@RequiredArgsConstructor
public class MyCreditCardController {

    private final CreditCardManagementService creditCardManagementService;
    private final CreditCardQueryService creditCardQueryService;
    private final SecurityChecks securityChecks;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityAnnotations.CanWriteMyCreditCards
    public CreditCardOutput register(@RequestBody @Valid TokenizedCreditCardInput input) {
        UUID customerId = securityChecks.getAuthenticatedUserId();
        input.setCustomerId(customerId);
        UUID creditCardId = creditCardManagementService.register(input);
        return creditCardQueryService.findOne(customerId, creditCardId);
    }

    @GetMapping
    @SecurityAnnotations.CanReadMyCreditCards
    public List<CreditCardOutput> findAllByCustomer() {
        return creditCardQueryService.findByCustomer(securityChecks.getAuthenticatedUserId());
    }

    @GetMapping("/{creditCardId}")
    @SecurityAnnotations.CanReadMyCreditCards
    public CreditCardOutput findOne(@PathVariable UUID creditCardId) {
        return creditCardQueryService.findOne(securityChecks.getAuthenticatedUserId(), creditCardId);
    }

    @DeleteMapping("/{creditCardId}")
    @SecurityAnnotations.CanWriteMyCreditCards
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID creditCardId) {
        creditCardManagementService.delete(securityChecks.getAuthenticatedUserId(), creditCardId);
    }

}
