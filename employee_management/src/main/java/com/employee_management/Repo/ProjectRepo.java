package com.employee_management.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee_management.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, Integer>{
	 
	public Project findProjectByName(String name);

}
