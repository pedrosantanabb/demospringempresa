package com.aurorastudio.demo.controllers;

import com.aurorastudio.demo.entities.Employee;
import com.aurorastudio.demo.exceptions.EmployeeDataViolationException;
import com.aurorastudio.demo.exceptions.EmployeeNotFoundException;
import com.aurorastudio.demo.exceptions.EmployeeNullException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class EmployeControllerTest {

    @Autowired
    EmployeeController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test()
    public void whenFindEmployeeById_thenThrowEmployeeNotFoundExceptionError(){
        EmployeeNotFoundException e = Assertions.assertThrows(EmployeeNotFoundException.class, () ->{
            controller.getEmployeeById(35l);
        });

        Assertions.assertEquals("Could not find employee 35", e.getMessage());
    }

    @Test()
    public void whenFindEmployeeIdNull_thenThrowEmployeeDataViolationExceptionError(){
        EmployeeDataViolationException e = Assertions.assertThrows(EmployeeDataViolationException.class, () ->{
            controller.getEmployeeById(null);
        });

        Assertions.assertEquals("El campo: id, no puede ser null o vacío", e.getMessage());
    }

    @Test
    public void whenCreateSampleEmployee_thenReturnnotnullID(){
        Employee employee01 = controller.newEmployee(new Employee("empleado01", "empleado"));
        assertThat(employee01.getId()).isNotNull();
    }
    @Test()
    public void whenCreateEmployeeNull_thenThrowEmployeeNullExceptionError(){
        EmployeeNullException e = Assertions.assertThrows(EmployeeNullException.class, () ->{
            controller.newEmployee(null);
        });

        Assertions.assertEquals("El empleado no puede ser null", e.getMessage());
    }

    @Test()
    public void whenCreateEmployeeNameNull_thenThrowEmployeeDataViolationExceptionError(){
        EmployeeDataViolationException e = Assertions.assertThrows(EmployeeDataViolationException.class, () ->{
            controller.newEmployee(new Employee(null, "Empleado"));
        });

        Assertions.assertEquals("El campo: name, no puede ser null o vacío", e.getMessage());
    }
    @Test()
    public void whenCreateEmployeeRoleNull_thenThrowEmployeeDataViolationExceptionError(){
        EmployeeDataViolationException e = Assertions.assertThrows(EmployeeDataViolationException.class, () ->{
            controller.newEmployee(new Employee("empleado01", null));
        });

        Assertions.assertEquals("El campo: role, no puede ser null o vacío", e.getMessage());
    }

    @Test
    public void whenUpdateNameEmployee_thenAssertChangeNameEqualsEmpleado02(){
        Employee employee01 = controller.newEmployee(new Employee("empleado01", "empleado"));
        employee01.setName("Empleado02");
        employee01 = controller.updateEmployee(employee01, employee01.getId());
        assertThat(employee01.getName()).isEqualTo("Empleado02");
    }

    @Test
    public void whenUpdateRoleEmployee_thenAssertChangeRoleEqualsAdmin(){
        Employee employee01 = controller.newEmployee(new Employee("empleado01", "empleado"));
        employee01.setRole("Admin");
        employee01 = controller.updateEmployee(employee01, employee01.getId());
        assertThat(employee01.getRole()).isEqualTo("Admin");
    }

    @Test
    public void whenUpdateNullEmployee_thenThrowEmployeeNullExceptionError(){
        final Employee employee01 = controller.newEmployee(new Employee("empleado01", "empleado"));


        EmployeeNullException e = Assertions.assertThrows(EmployeeNullException.class, () ->{
            controller.updateEmployee(null, employee01.getId());
        });

        Assertions.assertEquals("El empleado no puede ser null", e.getMessage());
    }

    @Test
    public void whenUpdateIDNull_thenThrowEmployeeDataViolationExceptionError(){
        final Employee employee01 = controller.newEmployee(new Employee("empleado01", "empleado"));

        EmployeeDataViolationException e = Assertions.assertThrows(EmployeeDataViolationException.class, () ->{
            controller.updateEmployee(new Employee(employee01.getName(), "Admin"),null);
        });

        Assertions.assertEquals("El campo: id, no puede ser null o vacío", e.getMessage());
    }

    @Test
    public void whenUpdateIDWrong_thenThrowEmployeeNotFoundExceptionError(){
        final Employee employee01 = controller.newEmployee(new Employee("empleado01", "empleado"));

        EmployeeNotFoundException e = Assertions.assertThrows(EmployeeNotFoundException.class, () ->{
            controller.updateEmployee(new Employee(employee01.getName(), "Admin"), 25l);
        });

        Assertions.assertEquals("Could not find employee 25", e.getMessage());
    }

    ///TODO Actualizar empleados con items


}
