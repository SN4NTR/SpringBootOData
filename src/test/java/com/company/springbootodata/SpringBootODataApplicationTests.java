package com.company.springbootodata;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class SpringBootODataApplicationTests {

    @LocalServerPort
    private int actualServerPort;

    @Test
    void portChecking() {
        int expectedServerPort = 8080;

        assertEquals(expectedServerPort, actualServerPort);
    }
}
