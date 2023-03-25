package com.paypal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.exceptions.ResourceNotFoundException;
import com.paypal.models.Sprint;
import com.paypal.models.Task;
import com.paypal.repo.SprintRepository;
import com.paypal.repo.TaskRepository;

@RestController
@RequestMapping("/sprints")
public class SprintController {

    @Autowired
    private SprintRepository sprintRepo;
    
    private TaskRepository taskRepo;

    @PostMapping
    public ResponseEntity<Sprint> createSprint(@RequestBody Sprint sprint) {
        Sprint newSprint = sprintRepo.save(sprint);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSprint);
    }

    @PostMapping("/{sprintId}/tasks")
    public ResponseEntity<Task> addTaskToSprint(@PathVariable Long sprintId, @RequestBody Task task) {
        Sprint sprint = sprintRepo.findById(sprintId).orElseThrow(() -> new ResourceNotFoundException("Sprint not found with id " + sprintId));
        task.setSprint(sprint);
        Task newTask = taskRepo.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    @GetMapping("/{sprintId}/tasks")
    public ResponseEntity<List<Task>> getTasksBySprint(@PathVariable Long sprintId) {
        Sprint sprint = sprintRepo.findById(sprintId).orElseThrow(() -> new ResourceNotFoundException("Sprint not found with id " + sprintId));
        List<Task> tasks = sprint.getTasks();
        return ResponseEntity.ok(tasks);
    }

}
