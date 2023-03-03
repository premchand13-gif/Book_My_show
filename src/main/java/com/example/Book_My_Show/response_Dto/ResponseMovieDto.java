package com.example.Book_My_Show.response_Dto;

import com.example.Book_My_Show.enums.Genre;
import com.example.Book_My_Show.enums.Languages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseMovieDto {
    private String movieName;
    private Genre genre;

    private Languages languages;
    private int duration;
    private double rating;
}
