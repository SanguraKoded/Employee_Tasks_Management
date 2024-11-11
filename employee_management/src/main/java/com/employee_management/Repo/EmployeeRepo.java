package com.employee_management.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee_management.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
