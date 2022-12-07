package com.example.cloudservice.security;

import com.example.cloudservice.model.User;
import com.example.cloudservice.repository.UserRepository;
import com.example.cloudservice.security.jwt.JwtUser;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceDet implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public UserServiceDet(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        log.info("IN loadUserByUsername - user {} is successfully loaded", username);
        return new JwtUser(user.get());
    }
}
