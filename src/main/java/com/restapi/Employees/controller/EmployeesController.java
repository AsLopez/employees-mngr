package com.restapi.Employees.controller;

import com.restapi.Employees.dto.EmployeeDto;
import com.restapi.Employees.exception.EmployeeException;
import com.restapi.Employees.service.IEmployeeService;
import com.restapi.Employees.utils.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("company")
public class EmployeesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeesController.class);

    private IEmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        LOGGER.info("INIT REQUEST TO GET ALL EMPLOYEES");
        try {
            List<EmployeeDto> result = employeeService.getEmployees();
            LOGGER.info("END REQUEST TO GET ALL EMPLOYEES - RESULT:{}", JacksonUtils.getPlainJson(result));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (EmployeeException employeeException) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/employee/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "id") int id) {
        LOGGER.info("[ID:{}] INIT REQUEST TO GET EMPLOYEE", id);
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            EmployeeDto result = employeeService.getEmployeeById(id);
            if (result == null) { // ToDo: Probar con id=40 o eliminar esta linea
                httpStatus = HttpStatus.NOT_FOUND;
            }
            LOGGER.info("[ID:{}] END REQUEST TO GET EMPLOYEE - RESULT:{}", id, JacksonUtils.getPlainJson(result));
            return new ResponseEntity<>(result, httpStatus);
        } catch (EmployeeException employeeException) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    public void setEmployeeService(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
