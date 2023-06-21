package com.restapi.Employees.controller;

import com.restapi.Employees.dto.EmployeeDto;
import com.restapi.Employees.exception.EmployeeException;
import com.restapi.Employees.service.IEmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class EmployeesControllerTest {
    @Mock
    IEmployeeService employeeService;
    @InjectMocks
    EmployeesController employeesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEmployees() throws EmployeeException {
        when(employeeService.getEmployees()).thenReturn(Arrays.asList(new EmployeeDto()));

        ResponseEntity<List<EmployeeDto>> result = employeesController.getEmployees();
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
    }

    @Test
    void testGetEmployeeById() throws EmployeeException {
        when(employeeService.getEmployeeById(anyInt())).thenReturn(new EmployeeDto());

        ResponseEntity<EmployeeDto> result = employeesController.getEmployeeById(2);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
    }
}

