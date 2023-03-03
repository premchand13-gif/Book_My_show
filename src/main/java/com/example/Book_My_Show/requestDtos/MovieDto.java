package com.example.Book_My_Show.requestDtos;

import com.example.Book_My_Show.enums.Genre;
import com.example.Book_My_Show.enums.Languages;
import lombok.Data;

@Data
public class MovieDto {
    private String movieName;

    private Genre genre;

    private Languages languages;
    private int duration;
    private double rating;
}
