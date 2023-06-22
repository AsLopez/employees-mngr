package com.restapi.Employees.utils;

import com.restapi.Employees.dto.EmployeeDto;

import java.util.List;

public class SalaryUtils {

    private static final int MONTHS = 12;

    private SalaryUtils() {
    }

    public static EmployeeDto getEmployeeWithAnnualSalary(EmployeeDto employeeDto) {
        int annualSalary = SalaryUtils.getAnnualSalary(employeeDto.getEmployee_salary());
        employeeDto.setEmployee_salary(annualSalary);
        return employeeDto;
    }

    public static List<EmployeeDto> getEmployeesWithAnnualSalary(List<EmployeeDto> employeeDtoList) {
        employeeDtoList.forEach(SalaryUtils::getEmployeeWithAnnualSalary);
        return employeeDtoList;
    }

    private static int getAnnualSalary(int salary) {
        return salary * MONTHS;
    }
}
