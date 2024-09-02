package com.example.coursework.test;

import com.example.coursework.model.Employee;

import java.util.function.Predicate;

public class EmployeePredicate implements Predicate<Employee> {


    @Override
    public boolean test(Employee employee) {
        return employee.getDepartment() > 1;
    }
}
