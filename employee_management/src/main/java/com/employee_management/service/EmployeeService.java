package com.employee_management.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee_management.Repo.EmployeeRepo;
import com.employee_management.entity.Employee;
import com.employee_management.enums.DepartmentsEnum;
import com.employee_management.exceptions.InvalidEmail;
import com.employee_management.exceptions.InvalidId;
import com.employee_management.exceptions.InvalidNameLength;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	public List<Employee> getAllEmployees(){
		List<Employee> employees = employeeRepo.findAll();
		
		employees.stream().peek(e -> e.setName(e.getName().toUpperCase())).collect(Collectors.toList());
			
		return employees;
		
	}
	
	public Employee getEmployeeById(int id) {
		try {
			
			Employee employee = employeeRepo.findById(id).orElseThrow(() -> new InvalidId("The Employee Id "+id+" is incorrect"));
			employee.getName().toUpperCase();
			return employeeRepo.save(employee);
		}
		catch(Exception e) {
			System.out.println("Unexpected Error Occured");
			return null;
		}
	}
	
	public Employee createEmployee(Employee employee) {
		
		try {
		Boolean emailValidity = employee.getEmail().endsWith("@gmail.com")||employee.getEmail().endsWith("@yahoo.com");
		if(emailValidity == false) {
			throw new InvalidEmail("The email entered is invalid");
		}
		String employeeName = employee.getName();
		employeeName.trim();
		int lengthName = employeeName.split("\\s+").length;
		if(lengthName<2) {
			throw new InvalidNameLength("Please Enter at least 2 names");
		}
		employee.getName().toUpperCase();
		return employeeRepo.save(employee);
		}
		catch(Exception e) {
			System.out.println("Unexpected Error Occured" +e.getMessage());
			return null;
		}
	}
	
	public String deleteEmployeeById(int id) {
		try {
			employeeRepo.findById(id).orElseThrow(() -> new InvalidId("Invalid Employee Id Entered"));
			employeeRepo.deleteById(id);
			return ("Successfully deleted Employee with Id"+id);
		}
		catch(Exception e) {
			System.out.println("Unexpected Error Occured" +e.getMessage());
			return null;
		}
	}
	
	public List<Employee> sortEmployeesByNames(){
		List<Employee> employees = employeeRepo.findAll();
		return employees.stream().sorted((e1,e2) -> e1.getName().compareTo(e2.getName())).collect(Collectors.toList());
	}
	
	public List<Employee> findEmployeesByDepartment(String department){
		department.toUpperCase();
		List<Employee> employees = employeeRepo.findAll();
		DepartmentsEnum departmentEnum = DepartmentsEnum.formString(department);
		return employees.stream().filter(e -> e.getDepartment() == departmentEnum).collect(Collectors.toList());
	}
	
	public List<Employee> findEmployeesByFirstNameLetter(String letter){
		letter.toUpperCase();
		List<Employee> employees = employeeRepo.findAll();
		return employees.stream().filter(e -> e.getName().startsWith(String.valueOf(letter))).collect(Collectors.toList());
	}
	
	public Employee updateEmployeeById(int id, Employee employee){
		try {
			employeeRepo.findById(id).orElseThrow(() -> new InvalidId("Invalid Employee Id Entered"));
			Boolean emailValidity = employee.getEmail().endsWith("@gmail.com") ||employee.getEmail().endsWith("@yahoo.com");
			if(emailValidity==false) {
				throw new InvalidEmail("Invalid email entered");
			}
			employee.getName().toUpperCase();
			int countNames = employee.getName().split("\\s+").length;
			
			if(countNames < 2) {
				throw new InvalidNameLength("Enter at least 2 names");
			}
			return employeeRepo.save(employee);
		}catch(Exception e) {
			System.out.println("Unexpected Error Occured "+e.getMessage());
			return null;
		}
	}
	
}
