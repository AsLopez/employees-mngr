package com.restapi.Employees.service.impl;

import com.restapi.Employees.dto.EmployeeApiResponseDto;
import com.restapi.Employees.dto.EmployeeDto;
import com.restapi.Employees.dto.EmployeeListApiResponseDto;
import com.restapi.Employees.exception.EmployeeException;
import com.restapi.Employees.service.IApiService;
import com.restapi.Employees.service.IEmployeeService;
import com.restapi.Employees.utils.SalaryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeService implements IEmployeeService {

    private String pathFullEmployees;
    private String pathEmployee;

    private IApiService apiService;

    @Override
    public List<EmployeeDto> getEmployees() throws EmployeeException {
        List<EmployeeDto> employeeDtoList = apiService.callEmployeeApi(pathFullEmployees, EmployeeListApiResponseDto.class).getData();
        SalaryUtils.getEmployeesWithAnnualSalary(employeeDtoList);
        return employeeDtoList;
    }

    @Override
    public EmployeeDto getEmployeeById(int id) throws EmployeeException {
        EmployeeDto employeeDto = apiService.callEmployeeApi(pathEmployee, id, EmployeeApiResponseDto.class).getData();
        SalaryUtils.getEmployeeWithAnnualSalary(employeeDto);
        return employeeDto;
    }

    @Value("${path.api.employees}")
    public void setPathFullEmployees(String pathFullEmployees) {
        this.pathFullEmployees = pathFullEmployees;
    }

    @Value("${path.api.employee}")
    public void setPathEmployee(String pathEmployee) {
        this.pathEmployee = pathEmployee;
    }

    @Autowired
    public void setApiService(IApiService apiService) {
        this.apiService = apiService;
    }
}
