package com.restapi.Employees.dto;

import java.util.List;

public class EmployeeListApiResponseDto extends BaseApiResponseDto {
    List<EmployeeDto> data;

    public List<EmployeeDto> getData() {
        return data;
    }

    public void setData(List<EmployeeDto> data) {
        this.data = data;
    }
}
