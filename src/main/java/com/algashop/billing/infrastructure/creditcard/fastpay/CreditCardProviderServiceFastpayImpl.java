package com.algashop.billing.infrastructure.creditcard.fastpay;

import com.algashop.billing.domain.model.creditcard.CreditCardProviderService;
import com.algashop.billing.domain.model.creditcard.LimitedCreditCard;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@ConditionalOnProperty(name = "algashop.integrations.payment.provider", havingValue = "FASTPAY")
public class CreditCardProviderServiceFastpayImpl implements CreditCardProviderService {
    @Override
    public LimitedCreditCard register(UUID customer, String tokenizedCard) {
        return null;
    }

    @Override
    public Optional<LimitedCreditCard> findById(String gatewayCode) {
        return Optional.empty();
    }

    @Override
    public void delete(String gatewayCode) {

    }
}
