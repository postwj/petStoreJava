package org.wisjedi.petstore.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wisjedi.petstore.exception.UserNotFoundException;
import org.wisjedi.petstore.infrastructure.UserRepository;
import org.wisjedi.petstore.model.User;

import java.util.List;

@RestController
@RequestMapping("v1/users")
public class UserController {

    private final UserRepository repository;


    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    List<User> all() {
        return repository.findAll();
    }

    @GetMapping("{username}")
    User getUserByUsername(@PathVariable( value="username") String username) {
        return repository.findById(username).orElseThrow(() -> new UserNotFoundException(username));
    }


}
