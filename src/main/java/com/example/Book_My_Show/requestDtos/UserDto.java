package com.example.Book_My_Show.requestDtos;

//import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDto {
    private String name;

    private String email;

    private String mobNo;
    private  int age;
}
