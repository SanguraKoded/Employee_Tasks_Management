package com.employee_management.entity;

import com.employee_management.enums.TaskStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private String taskName;
	
	@Column(nullable=false)
	private Employee assignedEmployees;
	
	@Enumerated(EnumType.STRING)
	private TaskStatus status;
	
	@Column(nullable=false)
	private int hoursRequired;
	
	@Column(nullable=false)
	@ManyToOne
	@JoinColumn(name="project_id")
	private Project project;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Employee getAssignedEmployees() {
		return assignedEmployees;
	}

	public void setAssignedEmployees(Employee assignedEmployees) {
		this.assignedEmployees = assignedEmployees;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public int getHoursRequired() {
		return hoursRequired;
	}

	public void setHoursRequired(int hoursRequired) {
		this.hoursRequired = hoursRequired;
	}
	
	

}
