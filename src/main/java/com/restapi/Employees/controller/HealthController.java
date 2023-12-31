package com.restapi.Employees.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class HealthController {

    @GetMapping("health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("EMPLOYEES_MNGR-OK");
    }
}