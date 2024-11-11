package com.employee_management.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee_management.Repo.EmployeeRepo;
import com.employee_management.Repo.ProjectRepo;
import com.employee_management.entity.Employee;
import com.employee_management.entity.Project;
import com.employee_management.enums.DepartmentsEnum;
import com.employee_management.exceptions.InvalidId;
import com.employee_management.exceptions.InvalidName;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepo projectRepo;
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	public List<Project> getAllProjects(){
		
		return projectRepo.findAll();
	}
	
	public Project findProjectById(int id) {
		try {
			return projectRepo.findById(id).orElseThrow(() -> new InvalidId("Given Id is Incorrect."));
		}
		catch(Exception e) {
			System.out.println("Unexpected Error Occured "+e.getMessage());
			return null;
		}
	}
	
	public List<Employee> getEmployeesOnProjects(String name){
		try {
			Project project = projectRepo.findProjectByName(name);
			if(project == null) {
				throw new InvalidName("Given Project Name is Invalid");
			}
			List<Employee> employees = employeeRepo.findAll();
			return employees.stream().filter(e -> e.getProject().getId() == (project.getId())).collect(Collectors.toList());

		}
		catch(Exception e) {
			System.out.println("Unexpected Error Occured "+e.getMessage());
			return null;
		}
	}
	
	public List<Employee> getEmployeesInDepartment(DepartmentsEnum department){
		try {
			List<Employee> employees = employeeRepo.findAll();
			return employees.stream().filter(e -> e.getDepartment().equals(department)).collect(Collectors.toList());
		}
		catch(Exception e) {
			System.out.println("Unexpected Error Occured "+e.getMessage());
			return null;
		}
	}
	
	public Map<DepartmentsEnum, List<Project>> groupProjectsByDepartment(DepartmentsEnum department){
		List<Project> projects = projectRepo.findAll();
		return projects.stream().collect(Collectors.groupingBy(Project :: getDepartment));
	}
	
	public List<String> uniqueProjectNames(){
		List<Project> projects = projectRepo.findAll();
		return projects.stream().map(Project::getName).distinct().collect(Collectors.toList());
	}
	
	public long numberOfProjects() {
		List<Project> projects = projectRepo.findAll();
		return projects.stream().map(Project::getId).distinct().count();
	}

}
