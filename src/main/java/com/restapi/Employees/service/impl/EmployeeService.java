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

    /*@Override
    public EmployeeDto getEmployeeById(int id) throws EmployeeException {
        try {
            UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromUriString(endpoint)
                    .path(pathEmployee.replace("{id}", String.valueOf(id)));
            URI uri = urlBuilder.build().encode().toUri();
            ResponseEntity<EmployeeApiResponseDto> response = restTemplate.getForEntity(uri, EmployeeApiResponseDto.class);
            return Objects.requireNonNull(response.getBody()).getData();
        } catch (RestClientException e) {
            LOGGER.error("[id:{}] No se encontro empleado para el id", id);
            throw new EmployeeException(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("[id:{}] No se pudo obtener respuesta", id, e);
            throw new EmployeeException(e.getMessage(), String.valueOf(id), e);
        }
    }*/

    /*@Override
    public List<EmployeeDto> getEmployees() throws EmployeeException {
        try {
            UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromUriString(endpoint)
                    .path(pathFullEmployees);
            URI uri = urlBuilder.build().encode().toUri();
            ResponseEntity<EmployeeListApiResponseDto> response = restTemplate.getForEntity(uri, EmployeeListApiResponseDto.class);
            return Objects.requireNonNull(response.getBody()).getData();
        } catch (RestClientException e) {
            LOGGER.error("No se encontraron empleados", e);
            throw new EmployeeException(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("No se pudo obtener respuesta", e);
            throw new EmployeeException(e.getMessage(), "NA", e);
        }
    }*/

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
