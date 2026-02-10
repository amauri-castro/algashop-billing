package com.algashop.billing.infrastructure.payment;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties("algashop.integrations.payment")
@Data
@Validated
public class AlgaShopPaymentProperties {

    @NotNull
    private AlgashopPaymentProvider provider;

    @NotNull
    @Valid
    private FastPayProperties fastpay;

    public enum AlgashopPaymentProvider {
        FAKE,
        FASTPAY
    }

    @Validated
    @Data
    public static class FastPayProperties {
        @NotBlank
        private String hostname;

        @NotBlank
        private String privateToken;
    }
}
