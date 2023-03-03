package com.example.Book_My_Show.ModelConverter;

import com.example.Book_My_Show.entities.User;
import com.example.Book_My_Show.requestDtos.UserDto;

public class UserConverter {
    public static User userDtoToUser(UserDto userDto){
        User user=User.builder().age(userDto.getAge()).email(userDto.getEmail())
                .mobNo(userDto.getMobNo()).name(userDto.getName()).build();
        return user;
    }
}
