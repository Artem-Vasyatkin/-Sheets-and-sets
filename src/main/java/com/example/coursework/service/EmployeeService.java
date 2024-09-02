package com.example.coursework.service;

import com.example.coursework.model.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee add(String firstName, String lastName, int department, double salary);

    Employee remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);

    Collection<Employee> findAll();
}

