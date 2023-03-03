package com.example.Book_My_Show.response_Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class ResponseTheaterDto {
    private String name;
    private String location;
}
