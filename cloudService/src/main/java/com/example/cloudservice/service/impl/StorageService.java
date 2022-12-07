package com.example.cloudservice.service.impl;

import com.example.cloudservice.model.User;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class StorageService {

    final Map<String, String> tokenStorage = new HashMap<>();



    public void login(String authToken, String username) {
        tokenStorage.put(authToken, username);
    }

    public Optional<User> logout(String authToken) {
        tokenStorage.remove(authToken);
        return null;
    }





}
