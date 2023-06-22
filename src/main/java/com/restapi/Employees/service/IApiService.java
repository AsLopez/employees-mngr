package com.restapi.Employees.service;

import com.restapi.Employees.exception.EmployeeException;

public interface IApiService {
    <T> T callEmployeeApi(String path, int id, Class<T> objectResponse) throws EmployeeException;

    default <T> T callEmployeeApi(String path, Class<T> objectResponse) throws EmployeeException {
        return callEmployeeApi(path, 0, objectResponse);
    }
}
