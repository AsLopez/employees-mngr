package com.restapi.Employees;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.Employees.dto.EmployeeApiResponseDto;
import com.restapi.Employees.dto.EmployeeDto;
import com.restapi.Employees.dto.EmployeeListApiResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ResourcesProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesProvider.class);

    private static final String ERROR_MESSAGE = "Error extracting object from Json source";
    private static final String GENERAL_MESSAGE = "Successfully! Record has been fetched.";
    private static final String SUCCESS_MESSAGE = "success";

    public static EmployeeDto createEmployeeDto() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode request;
            request = mapper.readTree(new File("src/test/resources/mocks/EmployeeDto.json"));
            return mapper.treeToValue(request, EmployeeDto.class);
        } catch (IOException e) {
            LOGGER.error(ERROR_MESSAGE, e);
            return null;
        }
    }

    public static EmployeeApiResponseDto buildEmployeeApiResponseDto() {
        EmployeeApiResponseDto employeeApiResponseDto = new EmployeeApiResponseDto();
        employeeApiResponseDto.setData(createEmployeeDto());
        employeeApiResponseDto.setMessage(GENERAL_MESSAGE);
        employeeApiResponseDto.setStatus(SUCCESS_MESSAGE);
        return employeeApiResponseDto;
    }

    public static EmployeeListApiResponseDto buildEmployeeListApiResponseDto() {
        EmployeeListApiResponseDto employeeListApiResponseDto = new EmployeeListApiResponseDto();
        employeeListApiResponseDto.setData(Arrays.asList(createEmployeeDto(), createEmployeeDto()));
        employeeListApiResponseDto.setMessage(GENERAL_MESSAGE);
        employeeListApiResponseDto.setStatus(SUCCESS_MESSAGE);
        return employeeListApiResponseDto;
    }
}