package com.aurorastudio.demo.services;

import com.aurorastudio.demo.entities.Employee;
import com.aurorastudio.demo.entities.Item;
import com.aurorastudio.demo.exceptions.EmployeeDataViolationException;
import com.aurorastudio.demo.exceptions.EmployeeNotFoundException;
import com.aurorastudio.demo.exceptions.EmployeeNullException;
import com.aurorastudio.demo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Employee findById(Long id){
        if(id == null) throw new EmployeeDataViolationException("id");
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Employee createEmployee(Employee employee){
        validateEmployee(employee);
        return repository.save(employee);
    }

    public Employee updateEmployee(Employee updatedEmployee, Long id){
        if(id == null) throw new EmployeeDataViolationException("id");
        validateEmployee(updatedEmployee);

        return repository.findById(id).map(creator -> {
            creator.setName(updatedEmployee.getName());
            creator.setRole(updatedEmployee.getRole());
            for (Item item: updatedEmployee.getItems()) {
                if(item.getId() == null){
                    creator.addItem(item);
                } else {
                    for(Item item1: creator.getItems()){
                        if(item1.getId().equals(item.getId())){
                            item1.setDescription(item.getDescription());
                            item1.setPrice(item.getPrice());
                            item1.setState(item.getState());
                        }
                    }
                }
            }
            Employee updateCreator =  repository.save(creator);
            return updateCreator;
        }).orElseGet(()->{
            throw new EmployeeNotFoundException(id);
        });
    }

    public void deleteEmployee(Long id){
        ///TODO en lugar de borrar poner un campo estado (ACTIVO, ELIMINADO) y cambiarlo
        repository.deleteById(id);
    }

    public void validateEmployee(Employee employee){
        if(employee == null) throw new EmployeeNullException();
        if(employee.getName() == null || employee.getRole() == null) throw new EmployeeDataViolationException((employee.getName()== null)?"name":"role");
        if(employee.getName().isEmpty() || employee.getRole().isEmpty()) throw new EmployeeDataViolationException((employee.getName()== null)?"name":"role");
    }
}
