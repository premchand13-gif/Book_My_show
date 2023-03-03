package com.example.Book_My_Show.services;

import com.example.Book_My_Show.ModelConverter.Movie_Converter;
import com.example.Book_My_Show.entities.Movie;
import com.example.Book_My_Show.entities.Show;
import com.example.Book_My_Show.repositories.MovieRepository;
import com.example.Book_My_Show.requestDtos.MovieDto;
import com.example.Book_My_Show.response_Dto.ResponseMovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public String addMovie(MovieDto movieDto){

        Movie movie= Movie_Converter.movieDtoToMovie(movieDto);
        movieRepository.save(movie);
        return "movie add successfully";
    }
    public ResponseMovieDto getMovieWithMoreShows(){
        List<Movie> movieList=movieRepository.findAll();
        int count=0;
        Movie movie=null;
        for(Movie m:movieList){
            List<Show> showList=m.getShowsList();
            if(showList.size()>count){
                count=showList.size();
                movie=m;

            }
        }

        return Movie_Converter.movieToMovieDto(movie);
    }
}
