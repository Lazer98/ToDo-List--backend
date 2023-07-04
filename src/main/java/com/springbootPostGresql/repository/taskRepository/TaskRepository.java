package com.springbootPostGresql.repository.taskRepository;


import com.springbootPostGresql.entity.Task;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("TaskRepository")
public interface TaskRepository extends JpaRepository<Task, Long> {

}
