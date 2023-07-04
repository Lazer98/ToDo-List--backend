package com.springbootPostGresql.service;


// Importing packages
// Importing required classes
import com.springbootPostGresql.entity.LoginRequest;
import com.springbootPostGresql.entity.User1;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

// Class
public interface UserService {

    // Save operation
    User1 createUser(User1 user);


    // Read operation
    List<User1> getAllUsers();

    //Read operation
    User1 getUserById(Long userId);

    ResponseEntity<?> getUserIdByEmail(@RequestBody LoginRequest loginRequest) ;



//    boolean fetchSingleUserByEmail(String email);

    // Update operation
    User1 updateUser(User1 user,
                     Long userId);

    // Delete operation
    void deleteUserById(Long userId);
}