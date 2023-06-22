package com.restapi.Employees.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class HealthControllerTest {
    HealthController healthController = new HealthController();

    @Test
    void testHealth() {
        ResponseEntity<String> result = healthController.health();
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals("EMPLOYEES_MNGR-OK", result.getBody());
    }
}
