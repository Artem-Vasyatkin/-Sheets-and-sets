package com.example.coursework.service;

import com.example.coursework.exception.EmployeeAlreadyAddedException;
import com.example.coursework.exception.EmployeeNotFoundException;
import com.example.coursework.model.Employee;
import com.example.coursework.validation.ParamValidationException;
import com.example.coursework.validation.ParameterValidator;
import com.github.javafaker.Faker;
import org.assertj.core.api.AbstractAtomicReferenceAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextDouble;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class EmployeeServiceImplTest {

    private final Faker faker = new Faker();
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl(new ParameterValidator());

    @Test
    void shouldAddEmployee_WhenCorrectParams_ThenAdd() {
        String firstName = "Иван";
        String lastName = "Иванов";
        int department = 1;
        double salary = 50000;

        //test
        Employee actual = employeeService.add(firstName, lastName, department, salary);

        //check
        assertThat(actual.getFirstName()).isEqualTo(firstName);
        assertThat(actual.getLastName()).isEqualTo(lastName);
        assertThat(actual.getDepartment()).isEqualTo(department);
        assertThat(actual.getSalary()).isEqualTo(salary);
    }

    @Test
    void shouldAddDuplicateEmployee_ThrowsException() {

        String firstName = "Иван";
        String lastName = "Иванов";
        int department = 1;
        double salary = 50000;

        // test
        employeeService.add(firstName, lastName, department, salary);

        // check
        Exception exception = assertThrows(EmployeeAlreadyAddedException.class, () -> {
            employeeService.add(firstName, lastName, department, salary);
        });

        assertThat(exception);
    }


    @Test
    void shouldAddEmployee_NullFirstName_ThrowsException() {

        String firstName = null;
        String lastName = "Иванов";
        int department = 1;
        double salary = 50000;

        // test && check
        assertThrows(ParamValidationException.class, () -> {
            employeeService.add(firstName, lastName, department, salary);
        });
    }

    @Test
    void shouldAddEmployee_NullLastName_ThrowsException() {

        String firstName = "Иван";
        String lastName = null;
        int department = 1;
        double salary = 50000;

        // test && check
        assertThrows(ParamValidationException.class, () -> {
            employeeService.add(firstName, lastName, department, salary);
        });
    }


    @Test
    void shouldRemoveEmployee_WhenEmployeeExist_ThenEmployeeRemoved() {

        String firstName = "Иван";
        String lastName = "Иванов";
        int department = 1;
        double salary = 50000;
        Employee expected = employeeService.add(firstName, lastName, department, salary);

        //test
        Employee actual = employeeService.remove(firstName, lastName);

        //check

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldRemove_NonExistentEmployee_ThrowsException() {

        String firstName = "Иван";
        String lastName = "Иванов";

        // test && check
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.remove(firstName, lastName);
        });

    }

    @Test
    void shouldFindEmployee_WhenEmployeeExist_ThenReturnEmployee() {
        String firstName = "Иван";
        String lastName = "Иванов";
        int department = 1;
        double salary = 50000;
        Employee expected = employeeService.add(firstName, lastName, department, salary);

        //test
        Employee actual = employeeService.find(firstName, lastName);

        //check

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldFind_NonExistentEmployee_ThrowsException() {

        String firstName = "Иван";
        String lastName = "Иванов";

        // test && check
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.find(firstName, lastName);
        });
    }


    @Test
    void findAll() {
        List<Employee> expected = new ArrayList<>();
        for (int i = 0; i < nextInt(1,10); i++) {

            expected.add(employeeService.add(faker.name().firstName(),
                    faker.name().lastName(),
                    nextInt(),
                    nextDouble()));
        }
        //test
        Collection<Employee> actual = employeeService.findAll();

        //check
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldFindAllEmployees_WhenEmptyMap_ThenReturnEmptyCollection() {
        //test
        Collection<Employee> actual = employeeService.findAll();
        //check
        assertThat(actual);
    }
}
