package org.wisjedi.petstore.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String username) {
        super("Can not find user with username: " + username);
    }
}
