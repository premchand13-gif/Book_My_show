package com.example.Book_My_Show.controllers;

import com.example.Book_My_Show.requestDtos.MovieDto;
import com.example.Book_My_Show.response_Dto.ResponseMovieDto;
import com.example.Book_My_Show.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService movieService;
    @PostMapping("/add_movie")
    public ResponseEntity<String> addMovie(@RequestBody MovieDto movieDto){

        String s=movieService.addMovie(movieDto);
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }
    @GetMapping("/get_movie")
    public ResponseEntity<ResponseMovieDto> getMovieWithMoreShows(){
        ResponseMovieDto movie=movieService.getMovieWithMoreShows();
        return new ResponseEntity<>(movie,HttpStatus.FOUND);
    }
}
