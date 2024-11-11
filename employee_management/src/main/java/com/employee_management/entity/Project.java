package com.employee_management.entity;

import java.util.List;

import com.employee_management.enums.DepartmentsEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@Enumerated(EnumType.STRING)
	private DepartmentsEnum department;
	
	@Column(nullable=false)
	@OneToMany(mappedBy="project")
	private List<Employee> employees;
	
	@Column(nullable=false)
	@OneToMany(mappedBy="project")
	private List<Task> tasks;

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DepartmentsEnum getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentsEnum department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", department=" + department + ", employees=" + employees + "]";
	}
	


}
