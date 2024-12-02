package com.example.demo.service;

import com.example.demo.entity.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {
    ResponseEntity<List<Task>> findAll(String status,Integer userId);

    void save(Task task);

    Task findById(int theId);

    void deleteById(int theId);

    Task update(int theId,Task task);



}
