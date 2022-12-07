package com.example.cloudservice.util.mapper;

import com.example.cloudservice.dto.UserDto;
import com.example.cloudservice.model.User;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserMapper {
    ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }



    public User convertToUser(UserDto userDto) {
        return  this.modelMapper.map(userDto, User.class);
    }

    public UserDto convertToUserDto(User user) {
        return this.modelMapper.map(user, UserDto.class);
    }
}
