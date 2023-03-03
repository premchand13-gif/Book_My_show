package com.example.Book_My_Show.controllers;

import com.example.Book_My_Show.requestDtos.TheatreDto;
import com.example.Book_My_Show.response_Dto.ResponseTheaterDto;
import com.example.Book_My_Show.services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatre")
public class TheaterController {
    @Autowired
    TheaterService theaterService;
    @PostMapping("/add_theatre")
    public ResponseEntity<String> addTheater(@RequestBody TheatreDto theatreDto){
        String s=theaterService.addTheater(theatreDto);
        return new ResponseEntity<>(s, HttpStatus.CREATED);

    }
    @GetMapping("/get_theater_list_movie")
    public ResponseEntity<List<ResponseTheaterDto>> getTheatersOfGivenMovie(@RequestParam int movieId){
        List<ResponseTheaterDto> list=theaterService.getTheatersByMovie(movieId);
        return new ResponseEntity<>(list,HttpStatus.FOUND);
    }
    @GetMapping("/get_unique_locations")
    public ResponseEntity<List<String>> getUniqueLocations(){
        return new ResponseEntity<>(theaterService.getUniqueLocations(),HttpStatus.FOUND);
    }
}
