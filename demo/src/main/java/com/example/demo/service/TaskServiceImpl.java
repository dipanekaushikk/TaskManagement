package com.example.demo.service;

import com.example.demo.dao.TaskRepository;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService {
    private UserService userService;
    private final TaskRepository taskRepository;
    private final Validator validator;

    @Autowired
    public TaskServiceImpl(TaskRepository theTaskRepository, Validator theValidator,UserService userService) {
        taskRepository = theTaskRepository;
        validator = theValidator;
        this.userService=userService;
    }

    @Override
    public ResponseEntity<List<Task>> findAll(String status,Integer userId) {
        List<Task> tasks=new ArrayList<>();

        if (status != null && userId != null) {
            User result=userService.findById(userId);
            if(result!=null)

            tasks = taskRepository.findByStatusAndAssignedTo(status, result);
        } else if (status != null) {
            tasks = taskRepository.findByStatus(status);
        } else if (userId != null) {
            User result=userService.findById(userId);
            tasks = taskRepository.findByAssignedTo(result);
        } else {
            tasks = (List<Task>) taskRepository.findAll();  // Fetch all tasks if no filter is provided
        }

        if (!tasks.isEmpty()) {
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(tasks, HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public void save(Task task) {
        validateTask(task);
        taskRepository.save(task);
    }

    @Override
    public Task findById(int theId) {
        Optional<Task> result = taskRepository.findById(theId);
        if (result.isEmpty()) {
            throw new RuntimeException("Did not find task with id " + theId);
        }
        return result.get();
    }

    @Override
    public void deleteById(int theId) {
        taskRepository.deleteById(theId);
    }

    @Override
    public Task update(int theId, Task task) {
        Optional<Task> result = taskRepository.findById(theId);
        if (result.isEmpty()) {
            throw new RuntimeException("Task not found with id - " + theId);
        }

        validateTask(task);

        Task existingTask = result.get();
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());
        return taskRepository.save(existingTask);
    }

    private void validateTask(Task task) {

        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        List<String> validStatuses = List.of("Pending", "In Progress", "Completed");
        if (!validStatuses.contains(task.getStatus())) {
            throw new IllegalArgumentException("Invalid status: " + task.getStatus() +
                    ". Allowed values: " + validStatuses);
        }
        if (task.getAssignedTo()==null){
            throw new IllegalArgumentException("Assigned to field is mandatory");
        }
    }
}
