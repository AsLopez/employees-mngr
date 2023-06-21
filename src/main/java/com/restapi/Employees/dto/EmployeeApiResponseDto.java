package com.restapi.Employees.dto;

public class EmployeeApiResponseDto extends BaseApiResponseDto {

    EmployeeDto data;

    public EmployeeDto getData() {
        return data;
    }

    public void setData(EmployeeDto data) {
        this.data = data;
    }
}
