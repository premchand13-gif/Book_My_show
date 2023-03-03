package com.example.Book_My_Show.services;

import com.example.Book_My_Show.ModelConverter.UserConverter;
import com.example.Book_My_Show.entities.Ticket;
import com.example.Book_My_Show.entities.User;
import com.example.Book_My_Show.repositories.UserRepository;
import com.example.Book_My_Show.requestDtos.UserDto;
import com.example.Book_My_Show.response_Dto.ResponseUserTicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public String createUser(UserDto userDto){
        User user= UserConverter.userDtoToUser(userDto);
        userRepository.save(user);
        return "user created successfully";
    }
    public List<ResponseUserTicketDto> getListOfTicketsOfUser(int userId){
        List<Ticket> ticketList=userRepository.findById(userId).get().getTicketList();
        List<ResponseUserTicketDto> userTicketDtoList=new ArrayList<>();
        for(Ticket t:ticketList){
           ResponseUserTicketDto responseUserTicketDto=new ResponseUserTicketDto();
           responseUserTicketDto.setTicketId(t.getTicketId());
           responseUserTicketDto.setMovieName(t.getMovieName());
           responseUserTicketDto.setTheaterName(t.getTheatreName());
           List<String> lst=List.of(t.getBookedSeats().split(","));
           responseUserTicketDto.setNumberOfSeats(lst.size());
           userTicketDtoList.add(responseUserTicketDto);
        }
        return userTicketDtoList;
    }
}
