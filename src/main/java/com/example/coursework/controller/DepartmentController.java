package com.example.coursework.controller;

import com.example.coursework.model.Employee;
import com.example.coursework.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee getMaxSalaryByDepartment(@RequestParam("departmentId") int departmentId) {
        return departmentService.getMaxSalaryByDepartment(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee getMinSalaryByDepartment(@RequestParam("departmentId") int departmentId) {
        return departmentService.getMinSalaryByDepartment(departmentId);
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployeeByDepartment(@RequestParam("departmentId") int departmentId) {
        return departmentService.getAllEmployeeByDepartment(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> getAllByDepartment() {
        return departmentService.getAllByDepartment();
    }
}
