package com.example.cloudservice.controllers;

import com.example.cloudservice.dto.AuthenticationDto;
import com.example.cloudservice.dto.UserDto;
import com.example.cloudservice.security.jwt.JwtUtils;
import com.example.cloudservice.service.impl.StorageService;
import com.example.cloudservice.service.impl.UserService;
import com.example.cloudservice.util.InvalidCredentials;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;




@AllArgsConstructor
@RestController
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    AuthenticationManager authenticationManager;
    JwtUtils jwtUtils;
    UserService userService;
    StorageService storageService;


    @PostMapping("/login")
     ResponseEntity<?> performLogin(@RequestBody @Valid AuthenticationDto authenticationDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
    new UsernamePasswordAuthenticationToken(authenticationDto.getUsername(),
            authenticationDto.getPassword());
        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (BadCredentialsException e) {
            throw new InvalidCredentials("Invalid username or password");
        }
        String token = jwtUtils.generateToken(authenticationDto.getUsername());
        storageService.login(token, authenticationDto.getUsername());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("auth-token") String authToken) {
        storageService.logout(authToken);
        return new ResponseEntity<>("Success logout", HttpStatus.OK);

    }


    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDto userDto) {
       UserDto user = userService.registration(userDto);
       return new ResponseEntity<>(user, HttpStatus.CREATED);



    }
}
