package com.example.Book_My_Show.requestDtos;

import lombok.Data;

import java.util.List;
@Data
public class TicketDto {
    private int showId;
    private int userId;
    private List<String> bookedSeats;

}
