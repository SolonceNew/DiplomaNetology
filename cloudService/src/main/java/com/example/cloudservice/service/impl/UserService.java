package com.example.cloudservice.service.impl;

import com.example.cloudservice.dto.UserDto;
import com.example.cloudservice.model.ERole;
import com.example.cloudservice.model.Role;
import com.example.cloudservice.model.User;
import com.example.cloudservice.repository.RoleRepository;
import com.example.cloudservice.repository.UserRepository;
import com.example.cloudservice.util.UseFoundException;
import com.example.cloudservice.util.mapper.UserMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional(readOnly = true)
@Service
public class UserService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    UserMapper userMapper;


    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;


    }

    @Transactional
    public UserDto registration(UserDto userDto) {
        final String username = userDto.getUsername();
        if(userRepository.findByUsername(username).isPresent()) {
            throw new UseFoundException(username);
        }
        User user = userMapper.convertToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName(ERole.ROLE_USER);
        user.setRole(role);
        UserDto newUserDto = userMapper.convertToUserDto(userRepository.save(user));
        log.info("User {} is successfully registered", user);
        return newUserDto;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    

}


