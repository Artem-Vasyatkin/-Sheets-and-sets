package com.example.coursework.service;

import com.example.coursework.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getMaxSalaryByDepartment(int departmentId) {
       return employeeService.findAll()
                .stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow();

    }

    @Override
    public Employee getMinSalaryByDepartment(int departmentId) {
       return employeeService.findAll()
                .stream()
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow();

    }

    @Override
    public List<Employee> getAllEmployeeByDepartment(int departmentId) {
       return employeeService.findAll()
                .stream()
                .filter(x -> x.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> getAllByDepartment() {
        return employeeService.findAll()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
