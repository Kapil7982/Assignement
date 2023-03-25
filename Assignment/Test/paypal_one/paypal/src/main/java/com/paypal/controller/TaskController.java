package com.paypal.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.paypal.exceptions.ResourceNotFoundException;
import com.paypal.models.Task;
import com.paypal.models.TaskStatus;
import com.paypal.models.User;
import com.paypal.repo.TaskRepository;
import com.paypal.repo.UserRepository;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private UserRepository userRepo;

    @PutMapping("/{taskId}/assignee")
    public ResponseEntity<Task> changeAssignee(@PathVariable Long taskId, @RequestParam Long assigneeId) {
        Task task = taskRepo.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));
        User assignee = userRepo.findById(assigneeId).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + assigneeId));
        task.setAssignee(assignee);
        Task updatedTask = taskRepo.save(task);
        return ResponseEntity.ok(updatedTask);
    }

    @PutMapping("/{taskId}/status")
    public ResponseEntity<Task> changeStatus(@PathVariable Long taskId, @RequestParam TaskStatus status) {
        Task task = taskRepo.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));
        task.setStatus(status);
        Task updatedTask = taskRepo.save(task);
        return ResponseEntity.ok(updatedTask);
    }

    @GetMapping("/assigned")
    public ResponseEntity<List<Task>> getTasksByAssignee(@RequestParam Long assigneeId) {
        User assignee = userRepo.findById(assigneeId).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + assigneeId));
        List<Task> tasks = assignee.getTasks();
        return ResponseEntity.ok(tasks);
    }

}
