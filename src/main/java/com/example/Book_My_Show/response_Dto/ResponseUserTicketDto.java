package com.example.Book_My_Show.response_Dto;

import lombok.Data;

@Data
public class ResponseUserTicketDto {
    private String ticketId;
    private String movieName;
    private String theaterName;
    private int numberOfSeats;
}
