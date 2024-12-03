package com.example.demo.service;

import com.example.demo.entity.User;
import com.fasterxml.jackson.core.util.RecyclerPool;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface UserService {
    ResponseEntity<List<User>> findAll();
    User findById(int theId);
    User save(User user);
    void delete(int theId);
    User update(int theId,User user);
}
