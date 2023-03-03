package com.example.Book_My_Show.requestDtos;

import lombok.Data;

@Data
public class TheatreDto {
    private String name;
    private String location;
    private int classicSeats;
    private int premiumSeats;
}
