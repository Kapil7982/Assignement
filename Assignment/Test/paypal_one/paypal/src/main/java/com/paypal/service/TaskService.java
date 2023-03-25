package com.paypal.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paypal.models.Sprint;
import com.paypal.models.Task;
import com.paypal.models.TaskStatus;
import com.paypal.models.TaskType;
import com.paypal.models.User;
import com.paypal.repo.SprintRepository;
import com.paypal.repo.TaskRepository;
import com.paypal.repo.UserRepository;

@Service
@Transactional
public class TaskService {
	
    private final TaskRepository taskRepo;

    public TaskService(TaskRepository taskRepo) {
        this.taskRepo = taskRepo;
    }

//    public List<Task> getAllTasksBySprintId(Long sprintId) {
//        return taskRepo.findBySprintId(sprintId);
//    }
//
//    public List<Task> getAllTasksByAssigneeId(Long assigneeId) {
//        return taskRepo.findByAssigneeId(assigneeId);
//    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepo.findById(id);
    }

    public void createTask(Task task) {
        taskRepo.save(task);
    }

    public void updateTask(Task task) {
        taskRepo.save(task);
    }

    public void deleteTaskById(Long id) {
        taskRepo.deleteById(id);
    }
    
    
}

