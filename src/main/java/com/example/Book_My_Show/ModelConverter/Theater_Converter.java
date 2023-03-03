package com.example.Book_My_Show.ModelConverter;

import com.example.Book_My_Show.entities.Theatre;
import com.example.Book_My_Show.requestDtos.TheatreDto;
import com.example.Book_My_Show.response_Dto.ResponseTheaterDto;

public class Theater_Converter {
    public static Theatre theatreDtoToTheater(TheatreDto theatreDto){
        Theatre theatre=Theatre.builder().name(theatreDto.getName()).location(theatreDto.getLocation())
                .build();
        return theatre;
    }
    public static ResponseTheaterDto theaterToTheaterDto(Theatre theatre){
        ResponseTheaterDto responseTheaterDto=ResponseTheaterDto.builder()
                .location(theatre.getLocation()).name(theatre.getName()).build();
        return responseTheaterDto;
    }
}
