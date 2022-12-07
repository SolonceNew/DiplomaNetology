package com.example.cloudservice.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class JwtResponse {
    String token;
    String tokenType = "Bearer";
    String login;
    List<String> roles;


    public JwtResponse(String token, String login, List<String> roles) {
        this.token = token;
        this.login = login;
        this.roles = roles;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class LoginRequest {
        String login;
        String password;
    }
}
