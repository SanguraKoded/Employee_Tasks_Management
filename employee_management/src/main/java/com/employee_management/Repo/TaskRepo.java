package com.employee_management.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee_management.entity.Task;

public interface TaskRepo extends JpaRepository<Task, Integer>{

}
