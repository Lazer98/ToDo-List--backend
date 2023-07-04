package com.springbootPostGresql.service;

// Java Program to Illustrate TaskService.java File

// Importing packages
// Importing required classes
import com.springbootPostGresql.entity.Task;
import com.springbootPostGresql.entity.User1;

import java.util.List;

// Class
public interface TaskService {

    // Save operation
    //Task saveTask(Task task,Long userId);

    boolean saveTaskForUser(Task task,long userId);

    // Read operation
    List<Task> fetchTaskList(long userId);

    // Update operation
    Task updateTask(Task task,
                                Long taskId,Long userId);

    // Delete operation
    void deleteTaskById(Long taskId,Long userId);
}