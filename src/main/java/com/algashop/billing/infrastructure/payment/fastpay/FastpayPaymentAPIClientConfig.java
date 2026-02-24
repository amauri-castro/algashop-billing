package com.algashop.billing.infrastructure.payment.fastpay;

import com.algashop.billing.infrastructure.payment.AlgaShopPaymentProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class FastpayPaymentAPIClientConfig {

    @Bean
    public FastpayPaymentAPIClient fastpayPaymentAPIClient(
            RestClient.Builder builder,
            AlgaShopPaymentProperties properties
    ) {
        var fastPayProperties = properties.getFastpay();

        RestClient restClient = builder.baseUrl(fastPayProperties.getHostname())
                .requestInterceptor((request, body, execution) -> {
                    request.getHeaders().add("Token", fastPayProperties.getPrivateToken());
                    return execution.execute(request, body);
                }).build();

        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();

        return proxyFactory.createClient(FastpayPaymentAPIClient.class);
    }
}
