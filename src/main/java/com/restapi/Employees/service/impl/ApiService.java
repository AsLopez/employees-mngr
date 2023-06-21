package com.restapi.Employees.service.impl;

import com.restapi.Employees.exception.EmployeeException;
import com.restapi.Employees.service.IApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class ApiService implements IApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiService.class);
    private String endpoint;
    private RestTemplate restTemplate;

    @Override
    public <T> T callEmployeeApi(String inputPath, int id, Class<T> objectResponse) throws EmployeeException {
        String path = inputPath.replace("{id}", String.valueOf(id));
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromUriString(endpoint)
                .path(path);
        URI uri = urlBuilder.build().encode().toUri();
        try {
            ResponseEntity<T> response = restTemplate.getForEntity(uri, objectResponse);
            return response.getBody();
        } catch (Exception e) {
            LOGGER.error("[id:{}] Could not get response", id, e);
            throw new EmployeeException(e.getMessage(), String.valueOf(id), e);
        }
    }

    @Value("${endpoint.api.employees}")
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
