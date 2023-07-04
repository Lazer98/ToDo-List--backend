package com.springbootPostGresql.controller;

// Java Program to Illustrate userController.java File

// Importing packages modules


import com.springbootPostGresql.entity.LoginRequest;
import com.springbootPostGresql.entity.User1;
import com.springbootPostGresql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }



    @GetMapping("")
    @CrossOrigin(origins = "*")
    public List<User1> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<User1> getUserById(@PathVariable Long id) {
        User1 user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/email")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> getUserIdByEmail(@RequestBody LoginRequest loginRequest) {
       return userService.getUserIdByEmail(loginRequest);
    }

    @PostMapping("")
    @CrossOrigin(origins = "*")
    public ResponseEntity<User1> createUser(@RequestBody User1 user) {
        User1 createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<User1> updateUser(@PathVariable Long id, @RequestBody User1 updatedUser) {
        User1 user = userService.updateUser(updatedUser ,id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
