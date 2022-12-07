package com.example.cloudservice.service.impl;

import com.example.cloudservice.model.User;
import com.example.cloudservice.repository.RoleRepository;
import com.example.cloudservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AdminServiceImpl  {

    UserRepository userRepository;




    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) {
            throw new RuntimeException("User is not found");
        }
        return user;
    }


    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        log.info("Admin successfully get list of users");
        return userList;

    }
}
