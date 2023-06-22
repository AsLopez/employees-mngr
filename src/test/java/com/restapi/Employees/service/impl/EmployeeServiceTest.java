package com.restapi.Employees.service.impl;

import com.restapi.Employees.dto.EmployeeDto;
import com.restapi.Employees.exception.EmployeeException;
import com.restapi.Employees.service.IApiService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static com.restapi.Employees.ResourcesProvider.buildEmployeeApiResponseDto;
import static com.restapi.Employees.ResourcesProvider.buildEmployeeListApiResponseDto;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {
    @Mock
    IApiService apiService;
    @InjectMocks
    EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.setEnvironmentVars();
    }

    @Test
    void testGetEmployees() throws EmployeeException {
        when(apiService.callEmployeeApi(any(), any())).thenReturn(buildEmployeeListApiResponseDto());

        List<EmployeeDto> result = employeeService.getEmployees();
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testGetEmployeeById() throws EmployeeException {
        when(apiService.callEmployeeApi(any(), anyInt(), any())).thenReturn(buildEmployeeApiResponseDto());

        EmployeeDto result = employeeService.getEmployeeById(4);
        Assertions.assertEquals(2, result.getId());
    }

    private void setEnvironmentVars() {
        employeeService.setPathEmployee("path-employee");
        employeeService.setPathFullEmployees("path-employees");
    }
}

