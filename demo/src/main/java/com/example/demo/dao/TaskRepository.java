package com.example.demo.dao;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findByStatusAndAssignedTo(String status, User assignedTo);
    List<Task> findByStatus(String status);
    List<Task> findByAssignedTo(User assignedTo);

}
