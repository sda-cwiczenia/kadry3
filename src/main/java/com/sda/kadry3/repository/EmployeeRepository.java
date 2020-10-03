package com.sda.kadry3.repository;

import com.sda.kadry3.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

    List<Employee> findByFirstName(String imie);
}
