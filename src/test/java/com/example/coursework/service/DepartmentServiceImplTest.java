package com.example.coursework.service;

import com.example.coursework.model.Employee;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.apache.commons.lang3.RandomUtils.nextDouble;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private static final Faker faker = new Faker();

    private static final Collection<Employee> employees = List.of(
            new Employee(faker.name().firstName(), faker.name().lastName(), nextDouble(), nextInt()),
            new Employee(faker.name().firstName(), faker.name().lastName(), nextDouble(), nextInt()),
            new Employee(faker.name().firstName(), faker.name().lastName(), nextDouble(), nextInt()));

    @Test
    void getMaxSalaryByDepartment() {
        Employee emp1 = new Employee("Иван", "Иванов", 50000, 1);
        Employee emp2 = new Employee("Петр", "Петров", 70000, 2);
        Employee emp3 = new Employee("Олег", "Олегович", 60000, 3);

        when(employeeService.findAll()).thenReturn(Arrays.asList(emp1, emp2, emp3));

        Employee result = departmentService.getMaxSalaryByDepartment(emp1.getDepartment());

        assertEquals(emp2, result);
    }

    @Test
    void getMinSalaryByDepartment() {
        Employee emp1 = new Employee("Иван", "Иванов", 50000, 1);
        Employee emp2 = new Employee("Петр", "Петров", 70000, 2);
        Employee emp3 = new Employee("Олег", "Олегович", 60000, 3);

        when(employeeService.findAll()).thenReturn(Arrays.asList(emp1, emp2, emp3));

        Employee result = departmentService.getMinSalaryByDepartment(1);

        assertEquals(emp1, result);
    }

    @Test
    public void getAllEmployeeByDepartment() {
        Employee emp1 = new Employee("Иван", "Иванов", 50000, 1);
        Employee emp2 = new Employee("Петр", "Петров", 70000, 2);
        Employee emp3 = new Employee("Олег", "Олегович", 60000, 3);


        when(employeeService.findAll()).thenReturn(Arrays.asList(emp1, emp2));

        List<Employee> result = departmentService.getAllEmployeeByDepartment(30);

        assertEquals(0, result.size());
    }

    @Test
    public void testGetAllEmployeeByDepartment_WithEmptyEmployeeList() {
        when(employeeService.findAll()).thenReturn(Collections.emptyList());

        List<Employee> result = departmentService.getAllEmployeeByDepartment(10);

        assertEquals(0, result.size());
    }

    @Test
    public void getAllByDepartment() {
        when(employeeService.findAll()).thenReturn(Collections.emptyList());

        Map<Integer, List<Employee>> result = departmentService.getAllByDepartment();

        assertEquals(0, result.size());
    }


}
