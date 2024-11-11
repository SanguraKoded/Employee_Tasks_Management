package com.employee_management.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee_management.Repo.EmployeeRepo;
import com.employee_management.Repo.ProjectRepo;
import com.employee_management.Repo.TaskRepo;
import com.employee_management.entity.Employee;
import com.employee_management.entity.Project;
import com.employee_management.entity.Task;
import com.employee_management.enums.TaskStatus;
import com.employee_management.exceptions.InvalidId;

@Service
public class TaskService {
	
	@Autowired
	TaskRepo taskRepo;
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	ProjectRepo projectRepo;

	public List<Task> getAllTasks(){
		return taskRepo.findAll();
	}
	
	public Task getTaskById(int id) {
		try {
			return taskRepo.findById(id).orElseThrow(() -> new InvalidId("Invalid Task Id Entered"));
			 
		}
		catch(Exception e) {
			System.out.println("Unexpected Error Occured "+e.getMessage());
			return null;
		}
	}
	
	public List<Task> filterTaskByStatus(TaskStatus status){
		List<Task> tasks = taskRepo.findAll();
		return tasks.stream().filter(t -> t.getStatus().equals(status)).collect(Collectors.toList());
		
	}
	
	public Map<TaskStatus, List<Task>>  groupTasksByStatus(){
		List<Task> tasks = taskRepo.findAll();
		return tasks.stream().collect(Collectors.groupingBy(Task::getStatus));
	}
	
	public List<Task> findTasksForEmployeeById(int id){
		try {
			Employee employee = employeeRepo.findById(id).orElseThrow(() -> new InvalidId("Invalid Employee Id Entered"));
			List<Task> tasks = taskRepo.findAll();
			return tasks.stream().filter(t -> t.getAssignedEmployees().getId()==employee.getId()).collect(Collectors.toList());
		}
		catch(Exception e) {
			System.out.println("Unexpected Error Occured "+ e.getMessage());
			return null;
		}
	}
	
	public double averageTasksTimeForProjectById(int id) {
		try {
		Project project = projectRepo.findById(id).orElseThrow(() -> new InvalidId("Given Project Id is Invalid"));
		return project.getTasks().stream().collect(Collectors.averagingInt(Task::getHoursRequired));
		}
		catch(Exception e) {
			System.out.println("Unexpected Error Occured: "+e.getMessage());
			return 0;
		}
		
	}
}
