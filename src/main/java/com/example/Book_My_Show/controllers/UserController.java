package com.example.Book_My_Show.controllers;

import com.example.Book_My_Show.requestDtos.UserDto;
import com.example.Book_My_Show.response_Dto.ResponseUserTicketDto;
import com.example.Book_My_Show.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/create_user")
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto){
        String s=userService.createUser(userDto);
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }
    @GetMapping("/user_all_tickets")
    public ResponseEntity<List<ResponseUserTicketDto>> getListOfTicketsOfUser(@RequestParam int userId){
        List<ResponseUserTicketDto> lst=userService.getListOfTicketsOfUser(userId);
        return new ResponseEntity<>(lst,HttpStatus.FOUND);
    }
}
