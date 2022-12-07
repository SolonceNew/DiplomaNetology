package com.example.cloudservice.dto;

import com.example.cloudservice.model.Role;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    @NotBlank(message = "username couldn't be empty")
    @Size(min=2, max=20, message = "username should be between 2-20 characters")
    String username;

    Role role;

    @NotBlank
    @Size(min=3, max=20, message = "a password should be between 3-20 characters")
    String password;

}
