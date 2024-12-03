package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private Validator validator;
    @Autowired
    public UserServiceImpl(UserRepository userRepository,Validator validator){
        this.userRepository=userRepository;
        this.validator=validator;
    }
    @Override
    public ResponseEntity<List<User>> findAll() {
        try{
            return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    @Override
    public User findById(int theId) {
        Optional<User> result=userRepository.findById(theId);
        if(result.isEmpty()){

            throw new RuntimeException("Did not find the user with the id-"+theId);
        }
        return result.get();
    }

    @Override
    public User save(User user) {

        validateUser(user);
        return userRepository.save(user);
    }

    @Override
    public void delete(int theId) {
        userRepository.deleteById(theId);
    }

    @Override
    public User update(int theId,@Valid User user) {
        Optional<User> result=userRepository.findById(theId);
        if(result.isEmpty()){
            throw  new RuntimeException("User not found with id-"+theId);
        }
        validateUser(user);
        User ex=result.get();
        ex.setFirstName(user.getFirstName());
        ex.setLastName(user.getLastName());
        ex.setTimeZone(user.getTimeZone());
        ex.setIsActive(user.getIsActive());
        return userRepository.save(ex);
    }
    private void validateUser(User user){
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        if(user.getFirstName()==""||user.getIsActive()==null){
            throw new IllegalArgumentException("first name can not be null");
        }


    }

}
