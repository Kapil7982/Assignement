package com.paypal.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paypal.models.Task;
import com.paypal.models.User;

public interface TaskRepository extends JpaRepository<Task, Long>{ 

	List<Task> findBySprintId(Long sprintId);
    List<Task> findByAssignee(String assignee);
	

}