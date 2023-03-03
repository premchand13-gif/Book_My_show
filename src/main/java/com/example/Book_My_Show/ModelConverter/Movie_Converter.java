package com.example.Book_My_Show.ModelConverter;

import com.example.Book_My_Show.entities.Movie;
import com.example.Book_My_Show.requestDtos.MovieDto;
import com.example.Book_My_Show.response_Dto.ResponseMovieDto;

public class Movie_Converter {
    public static Movie movieDtoToMovie(MovieDto movieDto){
        Movie movie=Movie.builder().movieName(movieDto.getMovieName()).genre(movieDto.getGenre())
                .rating(movieDto.getRating()).duration(movieDto.getDuration()).languages(movieDto.getLanguages())
                .build();
        return movie;
    }
    public static ResponseMovieDto movieToMovieDto(Movie movie){
        ResponseMovieDto responseMovieDto=ResponseMovieDto.builder().movieName(movie.getMovieName())
                .duration(movie.getDuration()).genre(movie.getGenre()).languages(movie.getLanguages())
                .rating(movie.getRating())
                .build();
        return responseMovieDto;
    }
}
