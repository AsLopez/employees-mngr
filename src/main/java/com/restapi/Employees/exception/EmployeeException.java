package com.restapi.Employees.exception;

public class EmployeeException extends Exception {

    public EmployeeException(String message) {
        super("EmployeeException --> " + message);
    }

    public EmployeeException(String message, String id, Exception e) {
        super("EmployeeException --> [ID:" + id + "] " + message, e);
    }

}
