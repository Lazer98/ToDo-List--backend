package com.springbootPostGresql.service;

// Java Program to Illustrate UserServiceImpl.java
// File

// Importing required packages




import com.springbootPostGresql.entity.LoginRequest;
import com.springbootPostGresql.entity.User1;
import com.springbootPostGresql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


//    private final UserRepository userRepository;

    @Autowired
    private UserRepository userRepository;

//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public List<User1> getAllUsers() {

        return userRepository.findAll();
    }

    public User1 getUserById(Long id) {
        Optional<User1> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public ResponseEntity<?> getUserIdByEmail(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        // Retrieve all users from the database
        List<User1> users = getAllUsers();

        // Iterate over each user and check if the email and password match
        for (User1 user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return ResponseEntity.ok(user.getId());
            }
        }
        // No matching user found
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    public User1 createUser(User1 user) {
        return userRepository.save(user);
    }

    public User1 updateUser( User1 updatedUser ,Long id) {
        Optional<User1> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User1 user = optionalUser.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            // update other properties if needed
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
