package com.employee_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee_management.entity.Employee;
import com.employee_management.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees(){
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id){
		
		return ResponseEntity.ok(employeeService.getEmployeeById(id));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable int id){
		employeeService.deleteEmployeeById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/sortNames")
	public ResponseEntity<List<Employee>> sortEmployeesNames(){
		return ResponseEntity.ok(employeeService.sortEmployeesByNames());
	}
	
	@GetMapping("/{department}")
	public ResponseEntity<List<Employee>> findEmployeesByDepartment(@PathVariable String department){
		return ResponseEntity.ok(employeeService.findEmployeesByDepartment(department));
	}
	
	@GetMapping("/{letter}")
	public ResponseEntity<List<Employee>> findEmployeesByFirstNameLetter(String letter){
		return ResponseEntity.ok(employeeService.findEmployeesByFirstNameLetter(letter));
	}
	
	@PostMapping("/create")
	public ResponseEntity<Employee> createEmployee(Employee employee){
		Employee newEmployee = employeeService.createEmployee(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
	}
	
	
	
	
	
	
	
}
