package com.sda.kadry3.service;

import com.sda.kadry3.model.Employee;
import com.sda.kadry3.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public void addEmployee(Employee employee) {
        repository.save(employee);
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }

}
