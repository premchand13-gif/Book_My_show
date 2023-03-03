package com.example.Book_My_Show.services;

import com.example.Book_My_Show.ModelConverter.Theater_Converter;
import com.example.Book_My_Show.entities.Movie;
import com.example.Book_My_Show.entities.Show;
import com.example.Book_My_Show.entities.Theatre;
import com.example.Book_My_Show.entities.TheatreSeat;
import com.example.Book_My_Show.enums.SeatType;
import com.example.Book_My_Show.repositories.MovieRepository;
import com.example.Book_My_Show.repositories.TheaterRepository;
import com.example.Book_My_Show.requestDtos.TheatreDto;
import com.example.Book_My_Show.response_Dto.ResponseTheaterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    MovieRepository movieRepository;


    public String addTheater(TheatreDto theatreDto){
        Theatre theatre= Theater_Converter.theatreDtoToTheater(theatreDto);

        List<TheatreSeat> seatList=createTheaterSeats(theatreDto,theatre);
        theatre.setTheatreSeats(seatList);
        theaterRepository.save(theatre);
        return "theater added successfully";

    }
    private List<TheatreSeat> createTheaterSeats(TheatreDto theatreDto,Theatre theatre){
        int cs=theatreDto.getClassicSeats();
        int ps=theatreDto.getPremiumSeats();
        List<TheatreSeat> seatList=new ArrayList<>();
        for(int i=1;i<=cs;i++){
            TheatreSeat theatreSeat=TheatreSeat.builder().seatNo(i+"c").seatType(SeatType.CLASSIC)
                    .theatre(theatre).build();
            seatList.add(theatreSeat);
        }
        for(int i=1;i<=ps;i++){
            TheatreSeat theatreSeat=TheatreSeat.builder().seatNo(i+"p").seatType(SeatType.PREMIUM)
                    .theatre(theatre).build();
            seatList.add(theatreSeat);
        }
        return seatList;
    }
    public List<ResponseTheaterDto> getTheatersByMovie(int movieId){
        Movie movie=movieRepository.findById(movieId).get();
        List<Show> showList=movie.getShowsList();
        HashSet<ResponseTheaterDto> hashSet=new HashSet<>();
        for(Show s:showList){
            ResponseTheaterDto responseTheaterDto=Theater_Converter.theaterToTheaterDto(s.getTheatre_show());
            hashSet.add(responseTheaterDto);
        }
        return new ArrayList<>(hashSet);
    }
    public List<String > getUniqueLocations(){
        List<Theatre> theatreList=theaterRepository.findAll();
        HashSet<String > hashSet=new HashSet<>();
        for(Theatre t:theatreList){
            hashSet.add(t.getLocation());
        }
        return new ArrayList<>(hashSet);
    }
}
