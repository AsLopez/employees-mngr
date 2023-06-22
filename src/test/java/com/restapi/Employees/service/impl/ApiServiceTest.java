package com.restapi.Employees.service.impl;

import com.restapi.Employees.dto.EmployeeApiResponseDto;
import com.restapi.Employees.dto.EmployeeListApiResponseDto;
import com.restapi.Employees.exception.EmployeeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static com.restapi.Employees.ResourcesProvider.buildEmployeeApiResponseDto;
import static com.restapi.Employees.ResourcesProvider.buildEmployeeListApiResponseDto;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ApiServiceTest {
    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    ApiService apiService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.setEnvironmentVars();
    }

    @Test
    void testCallEmployeeApi() throws EmployeeException {
        when(restTemplate.getForEntity(any(), any())).thenReturn(new ResponseEntity<>(buildEmployeeApiResponseDto(), HttpStatus.OK));

        EmployeeApiResponseDto result = apiService.callEmployeeApi("/path1", 2, EmployeeApiResponseDto.class);
        Assertions.assertEquals(buildEmployeeApiResponseDto().getData().getId(), result.getData().getId());
    }

    @Test
    void testCallEmployeeApi_FailService() {
        when(restTemplate.getForEntity(any(), any())).thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        assertThrows(EmployeeException.class, () -> apiService.callEmployeeApi("/path1", 2, EmployeeApiResponseDto.class));
    }

    @Test
    void testCallEmployeeApi2() throws EmployeeException {
        when(restTemplate.getForEntity(any(), any())).thenReturn(new ResponseEntity<>(buildEmployeeListApiResponseDto(), HttpStatus.OK));

        EmployeeListApiResponseDto result = apiService.callEmployeeApi("/path2", EmployeeListApiResponseDto.class);
        Assertions.assertEquals(buildEmployeeListApiResponseDto().getData().size(), result.getData().size());
        Assertions.assertEquals(buildEmployeeListApiResponseDto().getStatus(), result.getStatus());
        Assertions.assertEquals(buildEmployeeListApiResponseDto().getMessage(), result.getMessage());
    }

    @Test
    void testCallEmployeeApi2_FailService() {
        when(restTemplate.getForEntity(any(), any())).thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        assertThrows(EmployeeException.class, () -> apiService.callEmployeeApi("/path2", EmployeeListApiResponseDto.class));
    }

    private void setEnvironmentVars() {
        apiService.setEndpoint("test-endpoint");
    }
}
