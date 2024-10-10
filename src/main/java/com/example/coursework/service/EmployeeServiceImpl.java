package com.example.coursework.service;

import com.example.coursework.exception.EmployeeAlreadyAddedException;
import com.example.coursework.exception.EmployeeNotFoundException;
import com.example.coursework.model.Employee;
import com.example.coursework.validation.ParameterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees;

    @Autowired
    public EmployeeServiceImpl(ParameterValidator parameterValidator) {
        this.parameterValidator = parameterValidator;
        this.employees = new HashMap<>();
    }

    private final ParameterValidator parameterValidator;

    public EmployeeServiceImpl(Map<String, Employee> employees, ParameterValidator parameterValidator) {
        this.employees = employees;
        this.parameterValidator = parameterValidator;
    }


    @Override
    public Employee add(String firstName, String lastName, int department, double salary) {

        firstName = parameterValidator.checkAndCapitalize(firstName);
        lastName = parameterValidator.checkAndCapitalize(lastName);

        Employee employee = new Employee(firstName, lastName,salary, department);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {

        double salary = 0;
        int department = 0;
        Employee employee = new Employee(firstName, lastName, salary, department);

        if (employees.containsKey(employee.getFullName())) {
            return employees.remove(employee.getFullName());
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        if (employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
