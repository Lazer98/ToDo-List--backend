package com.springbootPostGresql.controller;

// Java Program to Illustrate taskController.java File

// Importing packages modules

import java.util.List;


import com.springbootPostGresql.entity.Task;
import com.springbootPostGresql.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Annotation
@RestController
// Class
public class TaskController {

    @Autowired
    private TaskService taskService;


    @CrossOrigin(origins = "*")
    @PostMapping("/tasks/{userId}")
    public ResponseEntity<String> saveTask(@RequestBody Task task, @PathVariable("userId") long userId) {

        boolean saved = taskService.saveTaskForUser(task, userId);

        if (saved) {
            return ResponseEntity.ok("Task saved successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Read operation
    @CrossOrigin(origins = "*")
    @GetMapping("/tasks/{userId}")
    public List<Task> fetchTaskList(@PathVariable("userId") long userId)
    {
        return taskService.fetchTaskList(userId);
    }

    // Update operation
    @CrossOrigin(origins = "http://localhost:8081")
    @PutMapping("/tasks/{id}/{userId}")

    public Task updatetask(@RequestBody Task task,
                     @PathVariable("id") Long taskId, @PathVariable("userId") Long userId)
    {
        return taskService.updateTask(
                task, taskId,userId);
    }

    // Delete operation
    @CrossOrigin(origins = "*")
    @DeleteMapping("/tasks/{id}/{userId}")

    public String deleteTaskById(@PathVariable("id")
                                       Long taskId, @PathVariable("userId")Long userId)
    {
        taskService.deleteTaskById(taskId,userId);
        return "Deleted Successfully";
    }
}