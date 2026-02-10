package com.algashop.billing.domain.model.creditcard;

import java.util.Optional;
import java.util.UUID;

public interface CreditCardProviderService {
    LimitedCreditCard register(UUID customer, String tokenizedCard);
    Optional<LimitedCreditCard> findById(String gatewayCode);
    void delete(String gatewayCode);
}
