package com.example.Book_My_Show.services;

import com.example.Book_My_Show.ModelConverter.Show_Converter;
import com.example.Book_My_Show.entities.*;
import com.example.Book_My_Show.enums.SeatType;
import com.example.Book_My_Show.repositories.MovieRepository;
import com.example.Book_My_Show.repositories.ShowRepository;
import com.example.Book_My_Show.repositories.TheaterRepository;
import com.example.Book_My_Show.requestDtos.ShowDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    ShowRepository showRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheaterRepository theaterRepository;
    public String addShow(ShowDto showDto){
        Show show= Show_Converter.ShowDtoToShow(showDto);
        Movie movie=movieRepository.findById(showDto.getMovieId()).get();
        Theatre theatre=theaterRepository.findById(showDto.getTheaterId()).get();
        show.setMovie(movie);
        show.setTheatre_show(theatre);
        List<ShowSeat> showSeatList=createShowSeats(showDto,show,theatre);
        show.setShowSeatList(showSeatList);

        showRepository.save(show);

        List<Show> showList=theatre.getShowList();
        showList.add(show);
        theatre.setShowList(showList);
        theaterRepository.save(theatre);

        List<Show> showList1=movie.getShowsList();
        showList1.add(show);
        movie.setShowsList(showList1);
        movieRepository.save(movie);

        return "Show added successfully";


    }
    private List<ShowSeat> createShowSeats(ShowDto showDto,Show show,Theatre theatre){
        List<ShowSeat> showSeatList=new ArrayList<>();
        List<TheatreSeat> theatreSeats=theatre.getTheatreSeats();
        for(TheatreSeat ts:theatreSeats){
            ShowSeat showSeat=new ShowSeat();
            showSeat.setSeatNo(ts.getSeatNo());
            showSeat.setSeatType(ts.getSeatType());
            showSeat.setShow_showSeat(show);
            showSeat.setBooked(false);
            if(ts.getSeatType().equals(SeatType.CLASSIC)){
                showSeat.setPrice(showDto.getClassicPrice());
            }
            else{
                showSeat.setPrice(showDto.getPremiumPrice());
            }
            showSeatList.add(showSeat);
        }
        return showSeatList;
    }
    public List<LocalTime> getShowTimingsOfGivenMovie_Theater(int movieId,int theaterId){
        Movie movie=movieRepository.findById(movieId).get();
        List<Show> showList=movie.getShowsList();
        List<LocalTime> showTimings=new ArrayList<>();

        for(Show show:showList){
            if(show.getTheatre_show().getId()==theaterId){
                showTimings.add(show.getShowTime());
            }
        }
        return showTimings;

    }
}
