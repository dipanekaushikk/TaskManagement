package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return userService.findAll();
    }
    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
        userService.save(user);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable(name = "id") int id){
        User user=userService.findById(id);
        return ResponseEntity.ok(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(name = "id")int id, @Valid @RequestBody User user){
        User updatedUser=userService.update(id,user);
        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") int id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
