package com.micropos.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.http.dsl.Http;
import org.springframework.stereotype.Component;

@Component
public class HttpOutBoundGateway {
    @Bean
    public IntegrationFlow outGate() {
        return IntegrationFlows.from("deliveryChannel")
                .handle(Http.outboundGateway("http://localhost:8085/api/deliveries")
                        .httpMethod(HttpMethod.GET)
                        .expectedResponseType(String.class))
                .get();
    }
}
