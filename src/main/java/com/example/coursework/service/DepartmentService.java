package com.example.coursework.service;

import com.example.coursework.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee getMaxSalaryByDepartment(int departmentId);

    Employee getMinSalaryByDepartment(int departmentId);

    List<Employee> getAllEmployeeByDepartment(int departmentId);

    Map<Integer, List<Employee>> getAllByDepartment();
}

