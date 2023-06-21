package com.restapi.Employees.service;

import com.restapi.Employees.dto.EmployeeDto;
import com.restapi.Employees.exception.EmployeeException;

import java.util.List;

public interface IEmployeeService {
    EmployeeDto getEmployeeById(int id) throws EmployeeException;

    List<EmployeeDto> getEmployees() throws EmployeeException;
}
