package com.client.ws.rasmooplus.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MailIntegrationImplTest {

    @Autowired
    private MailIntegration mailintegration;

    @Test
    void createCustomerWhenDtoOK() {
        mailintegration.send("fellipebordon.work@gmail.com", "TESTANDO", "Acesso liberado");
    }
}
