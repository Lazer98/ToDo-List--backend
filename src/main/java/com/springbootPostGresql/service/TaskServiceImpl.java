package com.springbootPostGresql.service;

// Java Program to Illustrate TaskServiceImpl.java
// File

// Importing required packages

// Importing required classes
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.springbootPostGresql.entity.Task;
;
import com.springbootPostGresql.entity.User1;
import com.springbootPostGresql.repository.UserRepository;
import com.springbootPostGresql.repository.taskRepository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


// Annotation
@Service
// Class implementing TaskService class
public class TaskServiceImpl implements TaskService {

    @Autowired
    @Qualifier("TaskRepository")
    private TaskRepository taskRepository;

    @Autowired
    @Qualifier("UserRepository")
    private UserRepository userRepository;


    @Transactional
    public boolean saveTaskForUser(Task task, long userId) {
        // Retrieve the user by their ID
        User1 user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return false;
        }

        // Set the user for the task
        task.setUser_id(user);
        task.setTaskDateCreated(LocalDate.now());
        task.setTaskDone(false);
        if(task.getTaskImportant()==null)
            task.setTaskImportant(false);

        // Save the task to the database
        taskRepository.save(task);

        // Update the user's task list
        user.getTasks().add(task);

        return true;
    }

    // Read operation
    @Override
    public List<Task> fetchTaskList(long userId) {
        User1 user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            // Handle the case where the user is not found
            return Collections.emptyList(); // or throw an exception, return null, etc.
        }

        return user.getTasks();
    }

    // Update operation
    @Override
    public Task updateTask(Task task, Long taskId, Long userId) {
        Task depDB = taskRepository.findById(taskId).orElse(null);

        if (depDB == null) {
            // Task not found, handle the error accordingly
            // You can throw an exception, return null, or customize the error message
            throw new IllegalArgumentException("Task not found");
        }

        // Check if the userId matches the userId of the task
        if (!Objects.equals(depDB.getUser_id().getId(), userId)) {
            throw new IllegalArgumentException("You are not allowed to update this task");
        }

        if (Objects.nonNull(task.getTaskName()) && !"".equalsIgnoreCase(task.getTaskName())) {
            depDB.setTaskName(task.getTaskName());
        }

        if (Objects.nonNull(task.getTaskDateUntil())) {
            depDB.setTaskDateUntil(task.getTaskDateUntil());
        }

        if (Objects.nonNull(task.getTaskDone())) {
            depDB.setTaskDone(task.getTaskDone());
        }

        if (Objects.nonNull(task.getTaskImportant())) {
            depDB.setTaskImportant(task.getTaskImportant());
        }

        return taskRepository.save(depDB);
    }

    // Delete operation
    @Override
    public void deleteTaskById(Long taskId,Long userId)
    {
        Task depDB = taskRepository.findById(taskId).orElse(null);

        if (depDB == null) {
            // Task not found, handle the error accordingly
            // You can throw an exception, return null, or customize the error message
            throw new IllegalArgumentException("Task not found");
        }

        // Check if the userId matches the userId of the task
        if (!Objects.equals(depDB.getUser_id().getId(), userId)) {
            throw new IllegalArgumentException("You are not allowed to delete this task" );
        }

        taskRepository.deleteById(taskId);
    }
}