package com.example.Book_My_Show.controllers;

import com.example.Book_My_Show.requestDtos.ShowDto;
import com.example.Book_My_Show.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("shows")
public class ShowController {
    @Autowired
    ShowService showService;
    @PostMapping("/add_show")
    public ResponseEntity<String> addShow(@RequestBody ShowDto showDto){
        String s=showService.addShow(showDto);
        return new ResponseEntity<>(s, HttpStatus.CREATED);
    }
    @GetMapping("get_show_timings_movie_and_theater")
    public ResponseEntity<List<LocalTime>> getShowTimingsByMovieTheater(@RequestParam int movieId,@RequestParam int theaterId){
        return new ResponseEntity<>(showService.getShowTimingsOfGivenMovie_Theater(movieId,theaterId),HttpStatus.FOUND);
    }
}
