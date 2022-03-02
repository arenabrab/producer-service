package com.example.producerservice;

import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.PactBrokerAuth;
import au.com.dius.pact.provider.spring.junit5.WebFluxTarget;
import com.example.producerservice.config.ProducerConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

@Provider("producer-service")
@PactBroker(url = "https://squaretrade.pactflow.io/", authentication = @PactBrokerAuth(token = "YHsHQiENwpB5w4EcYG_p_A"))
class ProducerPactTest {

    @BeforeEach
    void setup(PactVerificationContext context) {
        context.setTarget(new WebFluxTarget(new ProducerConfig().route()));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }
}
