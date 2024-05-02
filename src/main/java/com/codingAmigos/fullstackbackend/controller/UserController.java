package com.codingAmigos.fullstackbackend.controller;

import com.codingAmigos.fullstackbackend.domain.User;
import com.codingAmigos.fullstackbackend.exception.UserNotFoundException;
import com.codingAmigos.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User geUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) throws UserNotFoundException {
        return userRepository.findById(id).map(
                user-> {
                    user.setName(newUser.getName());
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(()-> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id) throws UserNotFoundException {
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id "+ id + "has been deleted successfully";
    }
}
