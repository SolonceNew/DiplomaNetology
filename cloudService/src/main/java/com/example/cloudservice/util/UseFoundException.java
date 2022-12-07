package com.example.cloudservice.util;

public class UseFoundException extends RuntimeException{
    public UseFoundException(String username) {
        super("user with such username already exists");
    }
}
