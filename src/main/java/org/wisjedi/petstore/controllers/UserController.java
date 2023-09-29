package org.wisjedi.petstore.controllers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wisjedi.petstore.controllers.assemblers.UserModelAssembler;
import org.wisjedi.petstore.exception.UserNotFoundException;
import org.wisjedi.petstore.infrastructure.UserRepository;
import org.wisjedi.petstore.model.User;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserRepository repository;
    private final UserModelAssembler assembler;


    public UserController(UserRepository repository, UserModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/")
    public CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> users = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @GetMapping("{username}")
    public EntityModel<User> getUserByUsername(@PathVariable(value = "username") String username) {
        return assembler.toModel(repository.findById(username).orElseThrow(() -> new UserNotFoundException(username)));
    }

    @PostMapping("/")
    ResponseEntity<?> newUser(@RequestBody User user) {
        EntityModel<User> entityModel = assembler.toModel(repository.save(user));
        return ResponseEntity.created(
                entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()
                ).body(entityModel);
    }
}
